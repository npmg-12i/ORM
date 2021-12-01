package edu.npmg.accessdb;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBAccessQueryProvider {
	
	private Connection connection;
	private SqlTypeProvider sqlTypeProvider;
	public DBAccessQueryProvider(String pathToFile, SqlTypeProvider sqlTypeProvider)
	{
		if(pathToFile == null || pathToFile.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			connection = DriverManager.getConnection(
	        		"jdbc:ucanaccess://" + pathToFile);
			
		} 
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		this.sqlTypeProvider = sqlTypeProvider;
	}
	
	/*
	public <T> T[] query(Class c, String query, String...args)
	{
		return null;
	}
	*/
	
	public ResultSet selectQuery(String query, String... args)
	{
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			for(int i = 0; i<args.length; i++)
			{
				statement.setString(i+1, args[i]);
			}
			return statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void tableChangeQuery(String query, String... args)
	{
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			for(int i = 0; i<args.length; i++)
			{
				statement.setString(i+1, args[i]);
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertData(Object o) throws IllegalArgumentException, IllegalAccessException
	{
		String tableName = o.getClass().getSimpleName()+"s";
		Class<?> c = o.getClass();
		Field[] fields = c.getDeclaredFields();
		List<String> fieldValues = new ArrayList<>();
		
		String query = "INSERT INTO "+tableName+" (";
		for(int i = 0; i<fields.length; i++)
		{
			fields[i].setAccessible(true);
			String originalName = fields[i].getName();
			if(originalName.equals("ID"))
				continue;
			String dbName = Character.toUpperCase(originalName.charAt(0))+
					originalName.substring(1);
			query += dbName;
			if(i < fields.length-1)
				query += ", ";
			else
				query += ") VALUES (";
			
			if(fields[i].get(o) == null)
				fieldValues.add(null);
			else
				fieldValues.add(fields[i].get(o).toString());
		}
		for(int i = 1; i<fields.length; i++)
		{
			query += "?";
			if(i<fields.length-1)	
				query += ", ";
			else
				query += ")";
		}
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			for(int i = 0; i<fieldValues.size(); i++)
			{
				statement.setString(i+1, fieldValues.get(i));
			}
			statement.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateData(Object o) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		String tableName = o.getClass().getSimpleName()+"s";
		Class<?> c = o.getClass();
		Field idField = c.getDeclaredField("ID");
		idField.setAccessible(true);
		int id = idField.getInt(o);
		
		String query = "UPDATE "+tableName+" SET ";
		
		Field[] fields = c.getDeclaredFields();
		
		List<String> fieldValues = new ArrayList<>();
		
		for(int i = 0; i < fields.length; i++)
		{
			if(fields[i].equals(idField))
			{
				continue;
			}
			fields[i].setAccessible(true);
			
			if(fields[i].get(o) != null)
				fieldValues.add(fields[i].get(o).toString());
			else
				fieldValues.add(null);
			
			String originalName = fields[i].getName();
			String dbName = Character.toUpperCase(originalName.charAt(0))+
					originalName.substring(1);
			query += dbName + " = ?";
			if(i != fields.length-1)
			{
				query += ",";
			}
			query += " ";
		}
		query += "WHERE ID = ?";
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			int i;
			for(i = 0; i<fieldValues.size(); i++)
			{
				statement.setString(i+1, fieldValues.get(i));
			}
			statement.setInt(i+1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteData(Object o) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		String tableName = o.getClass().getSimpleName()+"s";
		Class<?> c = o.getClass();
		Field idField = c.getDeclaredField("ID");
		idField.setAccessible(true);
		int id = idField.getInt(o);
		
		String query = "DELETE FROM "+tableName+" WHERE ID = ?";
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void saveObject(Object o) throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		Class<?> objectClass = o.getClass();
		String tableName = objectClass.getSimpleName() + "s";
		Field idField = objectClass.getDeclaredField("id");
		idField.setAccessible(true);
		String objectId = idField.get(o).toString();
		
		String ResultQuery = "SELECT COUNT(ID) as Count FROM " + tableName + " WHERE ID = " + objectId;
		PreparedStatement statement = connection.prepareStatement(ResultQuery);
		ResultSet resultSet = statement.executeQuery();
		int count = 0;
		while(resultSet.next())
		{
			count = Integer.parseInt(resultSet.getString("Count"));
		}
		
		if(count > 0)
		{
			updateData(o);
		}
		else 
		{
			insertData(o);
		}
	}
	
	public void createTable(Class<?> objectClass) throws SQLException, IllegalArgumentException, IllegalAccessException
	{
		String tableName = objectClass.getSimpleName() + "s";
		Field[]  fields = objectClass.getDeclaredFields();
		
		String query = "CREATE TABLE " + tableName + " (";
		
		
		Object o = null;
		try {
			Constructor<?> c =  objectClass.getDeclaredConstructor();
			c.setAccessible(true);
			o = c.newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < fields.length; i++)
		{
			fields[i].setAccessible(true);
			String columnName = Character.toUpperCase(fields[i].getName().charAt(0)) + fields[i].getName().substring(1);
			
			Object oo = fields[i].get(o);
			String columnType = sqlTypeProvider.getSqlType(fields[i].get(o).getClass().getSimpleName());
//			String columnType = sqlTypeProvider.getSqlType(fields[i].getAnnotatedType().toString());
//			if(columnName.equals("Id"))
//			{
//				columnType = "AUTOINCREMENT PRIMARY KEY";
//			}
			query += columnName + " ";
			query += columnType;
			if(i<fields.length-1)
			{
				query += ", ";
			}
			else
			{
				query += ")";
			}
			
		}
		System.out.print(query);
		PreparedStatement statement = connection.prepareStatement(query);
		statement.executeUpdate();
		
	}
}

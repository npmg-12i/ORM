package edu.npmg.accessdb;

import java.lang.annotation.Annotation;
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

import edu.npmg.accessdb.annotations.Autoincrement;
import edu.npmg.accessdb.annotations.DBType;
import edu.npmg.accessdb.annotations.ForeignKey;
import edu.npmg.accessdb.annotations.NotNull;
import edu.npmg.accessdb.annotations.PrimaryKey;
import edu.npmg.accessdb.annotations.Table;

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
	
	@Deprecated
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
	
	@Deprecated
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
		Class<?> c = o.getClass();
		String tableName = getTableName(c);
		
		Field[] fields = c.getDeclaredFields();
		List<String> fieldValues = new ArrayList<>();
		
		String query = "INSERT INTO " + tableName + " (";
		for (int i = 0; i < fields.length; i++)
		{
			fields[i].setAccessible(true);
			String originalName = fields[i].getName();
			
			PrimaryKey key = fields[i].getDeclaredAnnotation(PrimaryKey.class);
			if(key != null)
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
		
		for (int i = 1; i<fields.length; i++)
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
		Class<?> c = o.getClass();
		String tableName = getTableName(c);
		
		String query = "UPDATE "+tableName+" SET ";
		
		Field[] fields = c.getDeclaredFields();
		String id = null;
		List<String> fieldValues = new ArrayList<>();
		
		for(int i = 0; i < fields.length; i++)
		{	
			fields[i].setAccessible(true);

			if(fields[i].getDeclaredAnnotation(PrimaryKey.class) != null)
			{
				id = fields[i].get(o).toString();
				continue;
			}
			
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
		
		if (id == null)
			return;
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			int i;
			for(i = 0; i<fieldValues.size(); i++)
			{
				statement.setString(i+1, fieldValues.get(i));
			}
			statement.setString(i+1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteData(Object o) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Class<?> objectClass = o.getClass();
		String tableName = getTableName(objectClass);

		String id = getId(o);
		
		String query = "DELETE FROM "+tableName+" WHERE ID = ?";
		
		if (id == null)
		{
			return;
		}
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, id);
			statement.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void saveData(Object o) throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		Class<?> objectClass = o.getClass();
		String tableName = getTableName(objectClass);
		
		String objectId = getId(0);
		
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
	
	public void deleteTable(Class<?> objectClass) throws SQLException
	{
		String tableName = getTableName(objectClass);
		
		String query = "DROP TABLE "+tableName;
		PreparedStatement statement = connection.prepareStatement(query);
		statement.execute();
	}
	
	public void createTable(Class<?> objectClass) throws SQLException, IllegalArgumentException, IllegalAccessException
	{
		String tableName = getTableName(objectClass);
	
		Field[]  fields = objectClass.getDeclaredFields();
		
		String query = "CREATE TABLE " + tableName + " (";
	
		for(int i = 0; i < fields.length; i++)
		{
			fields[i].setAccessible(true);
			String columnName = Character.toUpperCase(fields[i].getName().charAt(0)) + fields[i].getName().substring(1);
			
			PrimaryKey pk = fields[i].getDeclaredAnnotation(PrimaryKey.class);
			Autoincrement auto = fields[i].getDeclaredAnnotation(Autoincrement.class);
			DBType type = fields[i].getDeclaredAnnotation(DBType.class);
			
			String columnType = sqlTypeProvider.getSqlType(type.type().getSimpleName());
			if(auto != null)
			{
				columnType = "AUTOINCREMENT";
			}
			if(pk != null)
			{
				columnType += " PRIMARY KEY";
			}
			query += columnName + " ";
			query += columnType;
			if(fields[i].getDeclaredAnnotation(NotNull.class)!=null) {
				query += " NOT NULL ";
			}
			if(fields[i].getDeclaredAnnotation(ForeignKey.class)!=null) {
				//CustomerID INTEGER NOT NULL CONSTRAINT FK_CustomerID REFERENCES tblCustomers (CustomerID)
				ForeignKey fk = fields[i].getDeclaredAnnotation(ForeignKey.class);
				query += "CONSTRAINT FK_" + fk.fieldName() + " REFERENCES " + fk.tableName() 
				+ "("+fk.fieldName()+")";  
			}
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
	
	private String getTableName(Class<?> objectClass)
	{
		String tableName = objectClass.getSimpleName() + "s";
		Table table = objectClass.getAnnotation(Table.class);
		if(table != null)
		{
			tableName = table.tableName();
		}
		return tableName;
	}
	
	private String getId(Object o)
	{
		String id = null;
		
		Field[] fields = o.getClass().getDeclaredFields();
		for (Field f : fields)
		{
			if (f.getDeclaredAnnotation(PrimaryKey.class) == null)
				continue;
			else
			{
				f.setAccessible(true);
				try 
				{
					id = f.get(o).toString();
				} 
				catch (IllegalArgumentException | IllegalAccessException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return id;
	}
}

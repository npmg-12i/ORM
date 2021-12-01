package edu.npmg.accessdb.test;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.npmg.accessdb.AccessSqlTypeProvider;
import edu.npmg.accessdb.DBAccessQueryProvider;
import edu.npmg.accessdb.models.Doctor;

public class TableCreateTest {

	public static void main(String[] args) 
	{
		DBAccessQueryProvider queryProvider = 
				new DBAccessQueryProvider(".\\DB\\emptyDataBase.mdb"
				, new AccessSqlTypeProvider());
		/*
		try {
			queryProvider.deleteTable(Doctor.class);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		Doctor doctor = new Doctor(30, "Georgi", "Petrov", true, 4);
		
		try {
			queryProvider.createTable(doctor.getClass());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		ResultSet result = queryProvider.selectQuery("SELECT * FROM Doctors");
		
		//Field[] fields = Doctor.class.getDeclaredFields();
		
		//String columnType = fields[2].getAnnotatedType().;
		//System.out.print(columnType);
//		try {
//			while(result.next())
//			{
//				int id = result.getInt("ID");
//				String firstName = result.getString("FirstName");
//				String lastName = result.getString("LastName");
//				boolean isHeadOfDepartment = result.getBoolean("HeadOfDepartment");
//				
//				System.out.printf("name: %s %s head of department: %b\n",
//						firstName, lastName, isHeadOfDepartment);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
//		try {
//			queryProvider.saveObject(doctor);
//		} catch (SQLException | IllegalArgumentException | IllegalAccessException | SecurityException | NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			queryProvider.createTable(Doctor.class);
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	}


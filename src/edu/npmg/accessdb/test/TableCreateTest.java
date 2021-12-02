package edu.npmg.accessdb.test;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.npmg.accessdb.AccessSqlTypeProvider;
import edu.npmg.accessdb.DBAccessQueryProvider;
import edu.npmg.accessdb.models.Doctor;

import edu.npmg.accessdb.models.Specialty;

public class TableCreateTest {

	public static void main(String[] args) 
	{
		DBAccessQueryProvider queryProvider = 
				new DBAccessQueryProvider("C:\\Users\\Admin\\Downloads\\emptyDataBase.mdb"
				, new AccessSqlTypeProvider());
		
		/*try {
			queryProvider.deleteTable(Doctor.class);
			queryProvider.deleteTable(Specialty.class);
		} catch (SQLException e1) {
			 //TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			queryProvider.createTable(Specialty.class);
			Specialty s = new Specialty(1,"Surgeon");
			
			queryProvider.insertData(s);
			
			
		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Doctor doctor = new Doctor(30, "Georgi", "Petrov", true, 4);
		Doctor doctor1 = new Doctor(13, "Petar" ,"Georgiev",true,1 );
		
		try {
			queryProvider.createTable(doctor.getClass());
			queryProvider.insertData(doctor1);
			
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
		
			
		
		
		
		
		
		
		//ResultSet result = queryProvider.selectQuery("SELECT * FROM Doctors");
		
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


package edu.npmg.accessdb.test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import edu.npmg.accessdb.DBAccessQueryProvider;
import edu.npmg.accessdb.models.Doctor;
import edu.npmg.accessdb.models.Patient;

public class ReflectionTest {

	public static void main(String[] args) {
		/*DBAccessQueryProvider provider = 
				new DBAccessQueryProvider(".\\DB\\emptyDataBase.mdb");
		//Doctor d = new Doctor(8, "Ivan", "Lilov", true, 4);
		Patient p = new Patient(8, "Kircho", "Mihaylov", "8806215344", Date.valueOf(LocalDate.now()), null, "Nothing", 4);
		System.out.println(p.getAdmitted());
		try {
			provider.insertData(p);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ResultSet result = provider.selectQuery("SELECT *"
				+ " FROM Patients");
		
		List<Doctor> doctors = new ArrayList<>();
		List<Patient> patients = new ArrayList<>();
		
		try {
			while(result.next())
			{
				int id = result.getInt("ID");
				String firstName = result.getString("FirstName");
				String lastName = result.getString("LastName");
				Date admitted = result.getDate("Admitted");
				Date signedOut = result.getDate("SignedOut");
				String personalID = result.getString("PersonalID");
				String diagnosis = result.getString("Diagnosis");
				int doctorID = result.getInt("DoctorID");
				
				patients.add(new Patient(id, firstName, lastName, personalID, admitted, signedOut,
						diagnosis, doctorID));
				
				/*System.out.printf("name: %s %s head of department: %b speciality: %s\n",
						firstName, lastName, isHeadOfDepartment, specialityName);*/
		/*	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(patients);
		
		Patient pp = patients.get(3);
		pp.setDiagnosis("Super happy");
		try {
			provider.updateData(pp);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		patients.clear();
		
		result = provider.selectQuery("SELECT *"
				+ " FROM Patients");
		
		try {
			while(result.next())
			{
				int id = result.getInt("ID");
				String firstName = result.getString("FirstName");
				String lastName = result.getString("LastName");
				Date admitted = result.getDate("Admitted");
				Date signedOut = result.getDate("SignedOut");
				String personalID = result.getString("PersonalID");
				String diagnosis = result.getString("Diagnosis");
				int doctorID = result.getInt("DoctorID");
				
				patients.add(new Patient(id, firstName, lastName, personalID, admitted, signedOut,
						diagnosis, doctorID));
				
				/*System.out.printf("name: %s %s head of department: %b speciality: %s\n",
						firstName, lastName, isHeadOfDepartment, specialityName);*/
		/*	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(patients.get(3));*/
	}

}

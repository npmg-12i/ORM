package edu.npmg.accessdb.models;

import java.sql.Date;
import java.time.LocalDate;

import edu.npmg.accessdb.annotations.Autoincrement;
import edu.npmg.accessdb.annotations.DBType;
import edu.npmg.accessdb.annotations.PrimaryKey;
import edu.npmg.accessdb.annotations.Table;

@Table(tableName="Patients")
public class Patient 
{
	@PrimaryKey
	@Autoincrement
	@DBType(type = int.class)
	private int ID;
	
	@DBType(type = String.class)
	private String firstName;
	
	@DBType(type = String.class)
	private String lastName;
	
	@DBType(type = String.class)
	private String personalID;
	
	//to-do
	private Date admitted;//LocalDateTime
	private Date signedOut;
	
	@DBType(type = String.class)
	private String diagnosis;
	
	//to-do
	private int doctorID;
	
	public Patient(int iD, String firstName, String lastName, String personalID, Date admitted,
			Date signedOut, String diagnosis, int doctorID) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.personalID = personalID;
		this.admitted = admitted;
		this.signedOut = signedOut;
		this.diagnosis = diagnosis;
		this.doctorID = doctorID;
	}
	
	private Patient()
	{
		this(0, "", "", "", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), "", 0);
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPersonalID() {
		return personalID;
	}
	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}
	public Date getAdmitted() {
		return admitted;
	}
	public void setAdmitted(Date admitted) {
		this.admitted = admitted;
	}
	public Date getSignedOut() {
		return signedOut;
	}
	public void setSignedOut(Date signedOut) {
		this.signedOut = signedOut;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public int getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	
	public String toString()
	{
		return String.format("%s %s %s ", getFirstName(), getLastName(), getDiagnosis());
	}
}

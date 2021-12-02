package edu.npmg.accessdb.models;

import edu.npmg.accessdb.annotations.Autoincrement;
import edu.npmg.accessdb.annotations.PrimaryKey;
import edu.npmg.accessdb.annotations.DBType;
import edu.npmg.accessdb.annotations.ForeignKey;
import edu.npmg.accessdb.annotations.NotNull;

public class Doctor {
	//test ssh
	@PrimaryKey
	@Autoincrement
	@DBType(type = int.class)
	@NotNull
	private int ID;
	
	@DBType(type = String.class)
	@NotNull
	private String firstName;
	
	@DBType(type = String.class)
	@NotNull
	private String lastName;
	
	@DBType(type = boolean.class)
	private boolean headOfDepartment;
	
	//@ForeignKey(tableName = "Specialtys", fieldName = "ID")
	@DBType(type = int.class)
	@NotNull
	private int specialty_ID;
	
	public Doctor(String firstName, String lastName, boolean headOfDepartment,
			int specialty)
	{
		this(0, firstName, lastName, headOfDepartment, specialty);
	}
	
	public Doctor(int id, String firstName, String lastName, boolean headOfDepartment,
			int specialty)
	{
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setHeadOfDepartment(headOfDepartment);
		setSpecialty(specialty);
	}
	
	public void setId(int id)
	{
		this.ID = id;
	}
	public int getId()
	{
		return ID;
	}
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	public boolean isHeadOfDepartment() 
	{
		return headOfDepartment;
	}
	public void setHeadOfDepartment(boolean headOfDepartment) 
	{
		this.headOfDepartment = headOfDepartment;
	}
	public int getSpecialty() 
	{
		return specialty_ID;
	}
	public void setSpecialty(int specialty) 
	{
		this.specialty_ID = specialty;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s %s - %d",
						getFirstName(), getLastName(), 
						getSpecialty());
	}
}

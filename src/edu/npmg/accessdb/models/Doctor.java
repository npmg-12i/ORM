package edu.npmg.accessdb.models;

import edu.npmg.accessdb.annotations.Autoincrement;
import edu.npmg.accessdb.annotations.PrimaryKey;
import edu.npmg.accessdb.annotations.DBType;

public class Doctor {
	
	@PrimaryKey
	@Autoincrement
	@DBType(type = int.class)
	private int ID;
	
	@DBType(type = String.class)
	private String firstName;
	
	@DBType(type = String.class)
	private String lastName;
	
	@DBType(type = boolean.class)
	private boolean headOfDepartment;
	
	//@ForeignKey
	@DBType(type = int.class)
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

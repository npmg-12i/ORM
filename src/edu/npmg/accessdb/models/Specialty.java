package edu.npmg.accessdb.models;

import edu.npmg.accessdb.annotations.*;

@Table(tableName="Specialties")
public class Specialty 
{
	 @PrimaryKey
	 @Autoincrement
	 @DBType(type = int.class)
	 private int id;
	
	 @DBType(type = String.class)
	 private String name;
	 
	 public Specialty(int id, String name)
	 {
		 setId(id);
		 setName(name);
	 }
	 
	 public void setId(int id)
	 {
		 this.id = id;
	 }
	 
	 public void setName(String name)
	 {
		 this.name = name;
	 }
}

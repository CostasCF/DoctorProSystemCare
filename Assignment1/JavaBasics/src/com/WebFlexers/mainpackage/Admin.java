package com.WebFlexers.mainpackage;

public class Admin extends Users {
	
	public String superuserPassword;
	
	public Admin(String username, String password, String superuserPassword ,String name, String surname) {
		super(username, password, name, surname);
    }

	public void InsertDoctor(Doctor doctor)
	{
		//Insert doctor to database
	}
}

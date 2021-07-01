package com.WebFlexers.models;

public class Admin extends Users {
	
	protected String superuserPassword;
	
	public Admin(String username, String password, String superuserPassword, String name, String surname) {
		super(username, password, name, surname);
		this.superuserPassword = superuserPassword;
    }
	
	public String getSuperuserPassword() {
		return superuserPassword;
	}

	public void setSuperuserPassword(String superuserPassword) {
		this.superuserPassword = superuserPassword;
	}	
	
	/**
     * Inserts Doctor to database
     */
	public void InsertDoctor(Doctor doctor)	{		
		System.out.println(doctor.getName() + " " + doctor.getSurname() + " inserted to database");
	}
	
	/**
     * Removes Doctor from database
     */
	public void DeleteDoctor(Doctor doctor)	{		
		System.out.println(doctor.getName() + " " + doctor.getSurname() + " removed from database");
	}	
}

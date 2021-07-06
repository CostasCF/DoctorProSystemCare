package com.WebFlexers.models;

import java.sql.*;
import java.util.List;

public class Admin extends User {
	
	protected String superuserPassword;
	private String email;

	public String getEmail() {
		return email;
	}

	/**
	 * An admin that is instantiated with data from a database
	 * @param resultSet : The data from the database
	 */
	public Admin(ResultSet resultSet) {
		try {
			username = resultSet.getString(2);
			password = resultSet.getString(3);
			email = resultSet.getString(4);
			firstName = resultSet.getString(5);
			surname = resultSet.getString(6);
		} catch (SQLException ex) {
			System.out.println("An error occured while connecting to database");
			System.out.println(ex.toString());
		}
	}

	public Admin(String username, String password, String name, String surname, String email) {
		super(username, password, name, surname);
		this.email = email;
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
		System.out.println(doctor.getFirstName() + " " + doctor.getSurname() + " inserted to database");
	}
	
	/**
     * Removes Doctor from database
     */
	public void DeleteDoctor(Doctor doctor)	{		
		System.out.println(doctor.getFirstName() + " " + doctor.getSurname() + " removed from database");
	}
}

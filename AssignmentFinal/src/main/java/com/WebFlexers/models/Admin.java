package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.PasswordAuthentication;
import com.WebFlexers.Query;

import java.sql.*;
import java.util.ArrayList;

public class Admin extends User implements IDatabaseSupport {
	
	protected String superuserPassword;
	private String email;
	private String adminID;

	public String getAdminID() {
		return adminID;
	}

	boolean isSuperUser;

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getEmail() {
		return email;
	}

	/**
	 * An admin that is instantiated with data from a database
	 * @param resultSet : The data from the database
	 */
	public Admin(ResultSet resultSet) {
		try {
			adminID = resultSet.getString(1);
			username = resultSet.getString(2);
			password = resultSet.getString(3);
			email = resultSet.getString(4);
			firstName = resultSet.getString(5);
			surname = resultSet.getString(6);
			isSuperUser = resultSet.getBoolean(7);
		} catch (SQLException ex) {
			System.out.println("An error occured while connecting to database");
			System.out.println(ex.toString());
		}
	}

	public Admin(String username, String password, String name, String surname, String email,String adminID,Boolean isSuperUser) {
		super(username, password, name, surname);
		this.email = email;
		this.adminID = adminID;
		this.isSuperUser = isSuperUser;
    }

	public boolean IsSuperUser() { return isSuperUser; }

	public void setSuperUser(boolean superUser) { isSuperUser = superUser; }

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
	public static void DeleteDoctor(Doctor doc)	{
		try{
		DatabaseManager database = new DatabaseManager();
		 doc  =  database.getDoctorByAmka(doc.getAmka());
		database.deleteDoctor(doc);
	//	System.out.println(doc.getFirstName() + " " + doc.getSurname() + " removed from database");
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	// Database related methods

	/**
	 * Adds this admin to the database
	 * @param query Determines by which attribute the admin will be searched
	 */
	@Override
	public void addToDatabase(Query query) {
		try {
			query.getStatement().setString(1, adminID);
			query.getStatement().setString(2, username);

			PasswordAuthentication cryptography = new PasswordAuthentication();
			String hashedPassword = cryptography.hash(password.toCharArray());
			query.getStatement().setString(3, hashedPassword);

			query.getStatement().setString(4, email);
			query.getStatement().setString(5, firstName);
			query.getStatement().setString(6, surname);
			query.getStatement().setBoolean(7, isSuperUser);

			query.getStatement().execute();

			query.getStatement().close();
		} catch (SQLException e) {
			System.out.println("An error occurred while trying to add an admin to the database");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Removes this admin from the database
	 */
	@Override
	public void removeFromDatabase(Query query) {
		try {
			query.getStatement().setString(1, adminID);
			query.getStatement().execute();
			System.out.println("Successfully deleted admin with id: " + adminID +" from the database");

			query.getStatement().close();
		} catch (SQLException e) {
			System.out.println("DatabaseManager: An error occured while deleting an admin from the database");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Search for an admin in the database
	 * @param query : The query that determines by which field the admin will be selected
	 * @return An admin created from the data provided by the database, or null if he doesn't exist
	 */
	public static Admin getFromDatabase(Query query) {
		try {
			ResultSet resultSet = query.getStatement().executeQuery();

			Admin admin = null;
			if (resultSet.next()) {
				admin = new Admin(resultSet);
			}

			query.getStatement().close();
			return admin;
		} catch (SQLException e) {
			System.out.println("An error occurred while getting an admin from the database");
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Gets all the admins from the database
	 * @param query : The query that gets all the admins from the database
	 * @return An ArrayList of type Admin or null if no admins are found
	 */
	public static ArrayList<Admin> getAllFromDatabase(Query query) {
		try {
			ResultSet resultSet = query.getStatement().executeQuery();
			ArrayList<Admin> admins = new ArrayList<>();

			// Add all the admins to an ArrayList
			while (resultSet.next()) {
				admins.add(new Admin(resultSet));
			}

			// Close the connection
			query.getStatement().close();

			// Return the ArrayList of admins or null if no admins are found
			if (admins.isEmpty())
				return null;
			else
				return admins;
		} catch (SQLException e) {
			System.out.println("An error occurred while getting all admins from the database");
			System.out.println(e.getMessage());
			return null;
		}
	}
}

package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.PasswordAuthentication;
import com.WebFlexers.Query;

import java.sql.*;
import java.util.ArrayList;

public class Admin extends User implements IDatabaseSupport {
	
	private String superuserPassword;
	private String email;
	private String adminID;
	boolean isSuperUser;

	public String getEmail() {
		return email;
	}

	/**
	 * An admin that is instantiated with data from a database
	 * @param resultSet : The data from the database
	 */
	public Admin(ResultSet resultSet) {
		try {
			adminID = resultSet.getString("admin_id");
			username = resultSet.getString("username");
			hashedPassword = resultSet.getString("password");
			email = resultSet.getString("email");
			firstName = resultSet.getString("first_name");
			surname = resultSet.getString("last_name");
			isSuperUser = resultSet.getBoolean("IsSuperUser");
		} catch (SQLException ex) {
			System.out.println("An error occurred while creating an admin from result set");
			System.out.println(ex.toString());
		}
	}

	public Admin(String username, String password, String name, String surname, String email,String adminID,Boolean isSuperUser) {
		super(username, password, name, surname);
		this.email = email;
		this.adminID = adminID;
		this.isSuperUser = isSuperUser;
    }

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public boolean IsSuperUser() { return isSuperUser; }

	public void setSuperUser(boolean isSupperUser) { this.isSuperUser = isSupperUser; }

	public String getSuperuserPassword() {
		return superuserPassword;
	}

	public void setSuperuserPassword(String superuserPassword) {
		this.superuserPassword = superuserPassword;
	}

	// Database related methods

	/**
	 * Adds this admin to the database
	 * @param connection A connection to the database
	 */
	@Override
	public void addToDatabase(Connection connection) {
		try {
			Query query = Query.addAdmin(connection);
			query.getStatement().setString(1, adminID);
			query.getStatement().setString(2, username);

			PasswordAuthentication cryptography = new PasswordAuthentication();
			String hashedPassword = cryptography.hash(this.hashedPassword.toCharArray());
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
	public void removeFromDatabase(Connection connection) {
		try {
			Query query = Query.removeAdmin(connection, adminID);
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
	public static ArrayList<Admin> getMultipleFromDatabase(Query query) {
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

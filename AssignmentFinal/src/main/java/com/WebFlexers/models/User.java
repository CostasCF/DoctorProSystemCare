package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;

import java.sql.*;

public class User {

    private String username;
    private String password;
    private String name;
    private String surname;
    private static int usersCounter = 0;

    public User() {}

    // Constructor
    public User(String username, String password, String firstname, String surname) {
        this.username = username;
        this.password = password;
        this.name = firstname;
        this.surname = surname;

        usersCounter += 1;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static int getUsersCounter() {
        return usersCounter;
    }

    // Functionality methods
    /**
     * Checks if a user with the given username and password exists in the database
     * @param username : The user's username
     * @param password : The user's password
     * @return True if a user with the given credentials matches one in the database and false otherwise
     */
    public static User login(String username, String password) {

        try {
            Connection connection = DatabaseManager.Connect();

            if (connection != null) {
                // Check if a patient with the given credentials exists
                Patient patient = DatabaseManager.getPatient(username, password, connection);

                if (patient != null) {
                    connection.close();
                    return patient;
                }

                // Check if a doctor with the given credentials exists
                Doctor doctor = DatabaseManager.getDoctor(username, password, connection);

                if (doctor != null) {
                    connection.close();
                    return doctor;
                }

                // Check if an admin with the given credentials exists
                Admin admin = DatabaseManager.getAdmin(username, password, connection);

                if (admin != null) {
                    connection.close();
                    return admin;
                }

                // If the user is not found return null
                return null;
            }
            else {
                return null;
            }

        }
        catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }

        return null;
    }

    /**
     * User logout
     */
    public void logout() {

    }
}

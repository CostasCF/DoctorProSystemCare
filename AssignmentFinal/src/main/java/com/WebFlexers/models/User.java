package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.PasswordAuthentication;
import com.WebFlexers.Query;

import javax.print.Doc;
import java.sql.Connection;
import java.sql.SQLException;

public class User {

    protected String username;
    protected String hashedPassword;
    protected String firstName;
    protected String surname;

    public User() {}

    // Constructor
    public User(String username, String hashedPassword, String firstname, String surname) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.firstName = firstname;
        this.surname = surname;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Checks if the given password reflects to the stored hash password
     * @param password The real password given by the user
     * @return True if the passwords match, false otherwise
     */
    public boolean validatePassword(String password) {
        // Check if the given password corresponds to the stored hash
        PasswordAuthentication crypto = new PasswordAuthentication();
        if (crypto.authenticate(password.toCharArray(), hashedPassword)) {
            System.out.println("Password match");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Check the database for a user with the given credentials
     * @return The user that is found or null if none exists
     */
    public static User login(String username, String password) {
        try {
            // Open connection to the database
            DatabaseManager databaseManager = new DatabaseManager();
            Connection connection = databaseManager.getConnection();

            // Check all kinds of users
            Patient patient = Patient.getFromDatabase(Query.getPatientByUsername(connection, username));
            if (patient != null && patient.validatePassword(password)) {
                databaseManager.closeConnection();
                return patient;
            }

            Doctor doctor = Doctor.getFromDatabase(Query.getDoctorByUsername(connection, username));
            if (doctor != null && doctor.validatePassword(password)) {
                databaseManager.closeConnection();
                return doctor;
            }

            Admin admin = Admin.getFromDatabase(Query.getAdminByUsername(connection, username));
            if (admin != null && admin.validatePassword(password)) {
                databaseManager.closeConnection();
                return admin;
            }
            else {
                databaseManager.closeConnection();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while logging in a user");
            System.out.println(e.getMessage());
            return null;
        }
    }

}

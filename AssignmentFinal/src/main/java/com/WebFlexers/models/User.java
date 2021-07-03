package com.WebFlexers.models;

import java.sql.*;
import java.util.Properties;

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

        String url = "jdbc:postgresql://ec2-52-209-134-160.eu-west-1.compute.amazonaws.com:5432/d35afkue7kt3ri";
        Properties props = new Properties();
        props.setProperty("user","dmupmilluzwkvw");
        props.setProperty("password","1f80c2791969210ee5777c436e20ee52ca006ee7f1c2dbfaf86baa32f976f2fa");

        try {
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connected Successfully to the database");

            // Check if a patient with the given credentials exists
            PreparedStatement preparedStatement = conn.prepareStatement("select * from patient where username=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Patient patient = new Patient(resultSet);
                conn.close();
                return patient;
            }

            // Check if a doctor with the given credentials exists
            preparedStatement = conn.prepareStatement("select * from doctor where username=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Doctor doctor = new Doctor(resultSet);
                conn.close();
                return doctor;
            }

            // Check if an admin with the given credentials exists
            preparedStatement = conn.prepareStatement("select * from admin where username=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Admin admin = new Admin(resultSet);
                conn.close();
                return admin;
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

package com.WebFlexers;

import com.WebFlexers.models.Admin;
import com.WebFlexers.models.Appointment;
import com.WebFlexers.models.Doctor;
import com.WebFlexers.models.Patient;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseManager {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    /**
     * Creates a new instance of DatabaseManager and connects to the database
     */
    public DatabaseManager() {
        try {
            String url = "jdbc:postgresql://ec2-54-73-68-39.eu-west-1.compute.amazonaws.com:5432/d8kt47qh55c24g";
            Properties props = new Properties();
            props.setProperty("user","snzbrrltdfagct");
            props.setProperty("password","618b0e656d64c06ca167eca5179abd8bd4b7f8e3295784547642c1a5a465464a");

            connection = DriverManager.getConnection(url, props);
            System.out.println("Connected Successfully to the database");

        } catch (SQLException e) {
            System.out.println("An error occured while connecting to the database");
        }

    }

    /**
     * Closes the connection to the database
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("An error occured while trying to terminate the connection to the database");
        }

    }

    /**
     * Searches the database for a user that is a patient
     * @param username : The username of the user
     * @param password : The password of the user
     * @return The corresponding patient if they exist and null otherwise
     */
    public Patient getPatient(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Patient\" where \"username\"=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the given password corresponds to the stored hash
            PasswordAuthentication crypto = new PasswordAuthentication();
            if (resultSet.next() && crypto.authenticate(password.toCharArray(), resultSet.getString(3))) {
                System.out.println("password match");
                return new Patient(resultSet);
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database");
            return null;
        }
    }

    /**
     * Searches the database for a user that is a patient
     * @param amka : The patient's amka
     * @return The corresponding patient if they exist and null otherwise
     */
    public Patient getPatient(String amka) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Patient\" where \"amka\"=?");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Patient(resultSet);
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("An error occured while connecting to the database");
            return null;
        }
    }

    /**
     * Searches the database for a user that is a doctor
     * @param username : The username of the user
     * @param password : The password of the user
     * @return The corresponding doctor if they exist and null otherwise
     */
    public Doctor getDoctor(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Doctor\" where \"username\"=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the given password corresponds to the stored hash
            PasswordAuthentication crypto = new PasswordAuthentication();
            if (resultSet.next() && crypto.authenticate(password.toCharArray(), resultSet.getString(3))) {
                return new Doctor(resultSet);
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("An error occured while connecting to the database");
            return null;
        }
    }

    /**
     * Searches the database for a user that is a doctor
     * @param amka : The doctor's amka
     * @return The corresponding doctor if they exist and null otherwise
     */
    public Doctor getDoctor(String amka) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Doctor\" where \"amka\"=?");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Doctor(resultSet);
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("An error occured while connecting to the database");
            return null;
        }
    }

    /**
     * Searches the database for a user that is an admin
     * @param username : The username of the user
     * @param password : The password of the user
     * @return The corresponding admin if they exist and null otherwise
     */
    public Admin getAdmin(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Admin\" where \"username\"=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the given password corresponds to the stored hash
            PasswordAuthentication crypto = new PasswordAuthentication();
            if (resultSet.next() && crypto.authenticate(password.toCharArray(), resultSet.getString(3))) {
                return new Admin(resultSet);
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("An error occured while connecting to the database");
            return null;
        }
    }

    /**
     * Inserts an admin to the database
     * @param admin : The admin object whose data will be inserted
     */
    public void registerAdmin(Admin admin) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
            ("insert into \"Admin\" (\"admin_id\", \"username\", \"password\", \"email\", \"first_name\", \"last_name\") " +
            "values (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "PG2345");
            preparedStatement.setString(2, admin.getUsername());

            PasswordAuthentication cryptography = new PasswordAuthentication();
            String hashedPassword = cryptography.hash(admin.getPassword().toCharArray());
            preparedStatement.setString(3, hashedPassword);

            preparedStatement.setString(4, admin.getEmail());
            preparedStatement.setString(5, admin.getName());
            preparedStatement.setString(6, admin.getSurname());

            preparedStatement.executeQuery();
            System.out.println("Successfully added admin to the database");
        } catch (SQLException e) {
            System.out.println("DatabaseManager: An error occured while registering an admin to the database");
            System.out.println(e.getMessage());
        }
    }
    // Ignore for now
    /*public static ArrayList<Appointment> getPatientAppointments(String amka, Connection connection) {

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM appointment WHERE patient_amka=?");
            statement.setString(1, amka);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("An error occured while connecting to the database");
            System.out.println(e.getErrorCode());
            return null;
        }

    }*/
}

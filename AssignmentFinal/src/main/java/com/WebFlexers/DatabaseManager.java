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

    /**
     * Connects to the database with the correct credentials
     * @return The connection to the database
     */
    public static Connection Connect() {
        try {
            String url = "jdbc:postgresql://ec2-54-73-68-39.eu-west-1.compute.amazonaws.com:5432/d8kt47qh55c24g";
            Properties props = new Properties();
            props.setProperty("user","snzbrrltdfagct");
            props.setProperty("password","618b0e656d64c06ca167eca5179abd8bd4b7f8e3295784547642c1a5a465464a");

            Connection connection = DriverManager.getConnection(url, props);
            System.out.println("Connected Successfully to the database");
            return connection;

        } catch (SQLException e) {
            System.out.println("An error occured while connecting to the database");
            return null;
        }

    }

    /**
     * Searches the database for a user that is a patient
     * @param username : The username of the user
     * @param password : The password of the user
     * @param connection : A connection to the database
     * @return The corresponding patient if they exist and null otherwise
     */
    public static Patient getPatient(String username, String password, Connection connection) {
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
     * @param connection : A connection to the database
     * @return The corresponding patient if they exist and null otherwise
     */
    public static Patient getPatient(String amka, Connection connection) {
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
     * @param connection : A connection to the database
     * @return The corresponding doctor if they exist and null otherwise
     */
    public static Doctor getDoctor(String username, String password, Connection connection) {
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
     * @param connection : A connection to the database
     * @return The corresponding doctor if they exist and null otherwise
     */
    public static Doctor getDoctor(String amka, Connection connection) {
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
     * @param connection : A connection to the database
     * @return The corresponding admin if they exist and null otherwise
     */
    public static Admin getAdmin(String username, String password, Connection connection) {
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

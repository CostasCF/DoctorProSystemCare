package com.WebFlexers;

import com.WebFlexers.models.*;

import javax.print.Doc;
import java.sql.*;
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
        createConnection();
    }

    public void createConnection() {
        try {
            String url = "jdbc:postgresql://ec2-54-73-68-39.eu-west-1.compute.amazonaws.com:5432/d8kt47qh55c24g";
            Properties props = new Properties();
            props.setProperty("user","snzbrrltdfagct");
            props.setProperty("password","618b0e656d64c06ca167eca5179abd8bd4b7f8e3295784547642c1a5a465464a");

            connection = DriverManager.getConnection(url, props);
            System.out.println("Connected Successfully to the database");

        } catch (SQLException e) {
            System.out.println("An error occured while connecting to the database");
            System.out.println(e.getMessage());
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
    public Patient validatePatient(String username, String password) {
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
     * Searches the database for a user with the given credential that is a patient
     * @param amka : The patient's amka
     * @return The corresponding patient if they exist and null otherwise
     */
    public Patient getPatientByAmka(String amka) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Patient\" where \"amka\"=?");
            preparedStatement.setString(1, amka);
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
     * Search the database for a patient with the given username
     * @param username : The patient's username
     * @return The patient if they exist and null otherwise
     */
    public Patient getPatientByUsername(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Patient\" where \"username\"=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Patient(resultSet);
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("DatabaseManager: An error occured while searching a patient by username on the database");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Searches the database for a user with the given credential that is a doctor
     * @param username : The username of the user
     * @param password : The password of the user
     * @return The corresponding doctor if they exist and null otherwise
     */
    public Doctor validateDoctor(String username, String password) {
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
    public Doctor getDoctorByAmka(String amka) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Doctor\" where \"amka\"=?");
            preparedStatement.setString(1, amka);
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
     * Search the database for a doctor with the given username
     * @param username : The doctor's username
     * @return The doctor if they exist and null otherwise
     */
    public Doctor getDoctorByUsername(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Doctor\" where \"username\"=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Doctor(resultSet);
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("DatabaseManager: An error occured while searching a doctor by username on the database");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Search the database for an admin with the given username
     * @param username : The admin's username
     * @return The admin if they exist and null otherwise
     */
    public Admin getAdminByUsername(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Admin\" where \"username\"=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Admin(resultSet);
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("DatabaseManager: An error occured while searching an admin by username on the database");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Searches the database for a user that is an admin
     * @param username : The username of the user
     * @param password : The password of the user
     * @return The corresponding admin if they exist and null otherwise
     */
    public Admin validateAdmin(String username, String password) {
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
     * Checks if a user with the given username and password exists in the database
     * @param username : The user's username
     * @param password : The user's password
     * @return True if a user with the given credentials matches one in the database and false otherwise
     */
    public User validateUser(String username, String password) {
        if (connection != null) {
            // Check if a patient with the given credentials exists
            Patient patient = validatePatient(username, password);

            if (patient != null) {
                return patient;
            }

            // Check if a doctor with the given credentials exists
            Doctor doctor = validateDoctor(username, password);

            if (doctor != null) {
                return doctor;
            }

            // Check if an admin with the given credentials exists
            Admin admin = validateAdmin(username, password);
            return admin;
        }
        // If the user is not found return null
        else {
            return null;
        }
    }

    /**
     * Searches the database for a user with the given username
     * @param username : The username
     * @return : The user if they exist and null otherwise
     */
    public User getUserByUsername(String username) {
        // Check each category one by one until the user is found
        Patient patient = getPatientByUsername(username);
        if (patient != null)
            return patient;

        Doctor doctor = getDoctorByUsername(username);
        if (doctor != null)
            return doctor;

        return getAdminByUsername(username);
    }

    /**
     * Inserts an patient to the database
     * @param patient : The admin object whose data will be inserted
     */
    public void registerPatient(Patient patient) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("insert into \"Patient\" (\"amka\", \"username\", \"password\", \"first_name\", \"last_name\", " +
                            "\"email\", \"phone_num\") values (?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, patient.getAmka());
            preparedStatement.setString(2, patient.getUsername());

            PasswordAuthentication cryptography = new PasswordAuthentication();
            String hashedPassword = cryptography.hash(patient.getPassword().toCharArray());
            preparedStatement.setString(3, hashedPassword);

            preparedStatement.setString(4, patient.getFirstName());
            preparedStatement.setString(5, patient.getSurname());
            preparedStatement.setString(6, patient.getEmail());
            preparedStatement.setString(7, patient.getPhoneNumber());

            preparedStatement.execute();
            System.out.println("Successfully added patient to the database");
        } catch (SQLException e) {
            System.out.println("DatabaseManager: An error occured while registering a patient to the database");
            System.out.println(e.getMessage());
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
            preparedStatement.setString(5, admin.getFirstName());
            preparedStatement.setString(6, admin.getSurname());

            preparedStatement.execute();
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

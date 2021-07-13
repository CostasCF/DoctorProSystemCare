package com.WebFlexers;

import com.WebFlexers.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class DatabaseManager {
    // An attribute that keeps track of open connections
    private static int countOpenConnections = 0;
    private Connection connection;

    /**
     * Creates a new instance of DatabaseManager and connects to the database
     */
    public DatabaseManager() {
        createConnection();
        countOpenConnections++;
    }

    // Getters
    public Connection getConnection() { return connection; }
    public static int getOpenConnectionsCount() { return countOpenConnections; }

    // Methods
    public void createConnection() {
        try {
            String url = "jdbc:postgresql://ec2-63-33-239-176.eu-west-1.compute.amazonaws.com:5432/da2pcm0gjc5hrv";
            Properties props = new Properties();
            props.setProperty("user","egclbbfjnxflqx");
            props.setProperty("password","1595f9de3818f63fd7a8373098250bc31ff7803dd70dbfe9ba868a1e001655e6");

            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Closes the connection to the database
     */
    public void closeConnection() {
        try {
            connection.close();
            countOpenConnections--;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to terminate the connection to the database");
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
     * Deletes a doctor from the database by his AMKA unique id.
     * @param doctor The doctor object whose data will be deleted
     */
    public void deleteDoctor(Doctor doctor)  {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"Doctor\" where \"amka\" = ?"  );
            preparedStatement.setString(1,doctor.getAmka());
            preparedStatement.execute();
            System.out.println("Successfully deleted doctor with AMKA:" +doctor.getAmka() +" from the database");

            preparedStatement.close();
        }catch (SQLException e){
            System.out.println("DatabaseManager: An error occured while deleting a doctor from the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets all doctors from the database
     * @return The doctor object whose data will be added to doctors' list later
     */
    public ArrayList<Doctor> getDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Doctor\"");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                doctors.add(new Doctor(resultSet));
            }
            return doctors;
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database");
            return null;
        }
    }


    /**
     * Gets all admins from the database
     * @return The admin object whose data will be added to doctors' list later
     */
    public ArrayList<Admin> getAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Admin\"");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                admins.add(new Admin(resultSet));
            }
            return admins;
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database");
            return null;
        }
    }

    /**
     * Gets the scheduled appointments of a doctor
     * @param doctor_amka AMKA type string
     * @return ArrayList<Appointment>
     */
    public ArrayList<ScheduledAppointment> getScheduledAppointmentsByDoctorAMKA(String doctor_amka)
    {
        ArrayList<ScheduledAppointment> appointments = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"Scheduled_Appointment\" where \"doctor_amka\"=?");
            preparedStatement.setString(1, doctor_amka);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new ScheduledAppointment(resultSet));
            }

            return appointments;
        }
        catch (SQLException e) {
            System.out.println("An error occured while fetching appointments from the database");
            return null;
        }
    }
}

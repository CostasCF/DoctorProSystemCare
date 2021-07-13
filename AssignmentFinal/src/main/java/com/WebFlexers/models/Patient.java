package com.WebFlexers.models;

import com.WebFlexers.PasswordAuthentication;
import com.WebFlexers.Query;

import java.sql.*;
import java.util.ArrayList;

public class Patient extends User implements IDatabaseSupport {

    private ArrayList<ScheduledAppointment> scheduledAppointments = new ArrayList<>();
    private final String amka;
    private String phoneNumber;
    private String email;

    /**
     * A patient that is instantiated with data from a database
     * @param resultSet : The data from the database
     */
    public Patient(ResultSet resultSet) {
        String tempAmka = "";
        try {
            tempAmka = resultSet.getString(1);
            setUsername(resultSet.getString(2));
            setFirstName(resultSet.getString(4));
            setSurname(resultSet.getString(5));
            email = resultSet.getString(6);
            phoneNumber = resultSet.getString(7);
            //scheduledAppointments = appointments;
        } catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println("test 3-4?");
        }
        amka = tempAmka;
    }

    public Patient(String amka, String username, String password, String firstname, String surname, String email,
                   String phoneNumber) {
        super(username, password, firstname, surname);
        this.amka = amka;
        this.phoneNumber = phoneNumber;
        this.email= email;
    }

    // Getters and setters
    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getAmka() { return amka; }

    public ArrayList<ScheduledAppointment> getScheduledAppointments() { return scheduledAppointments; }

    public void setScheduledAppointments(ArrayList<ScheduledAppointment> scheduledAppointments) { this.scheduledAppointments = scheduledAppointments; }

    /**
     * Adds an appointment to the schedule
     * @param appointment: The scheduled appointment between the doctor and the patient
     */
    public void addAppointment(ScheduledAppointment appointment) { scheduledAppointments.add(appointment); }

    /**
     * Removes appointment from the list of scheduled appointments
     * @param appointment: The scheduled appointment between the doctor and the patient
     */
    public void cancelAppointment(ScheduledAppointment appointment) { scheduledAppointments.remove(appointment); }

    /**
     * Replaces the old appointment with the new one in the scheduled appointments list
     */
    public void replaceAppointment(ScheduledAppointment oldAppointment, ScheduledAppointment newAppointment) {
        if (scheduledAppointments.contains(oldAppointment)) {
            scheduledAppointments.set(scheduledAppointments.indexOf(oldAppointment), newAppointment);
        }
    }

    /**
     * Registers a new patient to the database
     */
    public void register() {
        System.out.println("Patient registered");
    }

    /**
     * Finds the available appointments of the given doctor
     * @return ArrayList of type Appointment
     */
    public ArrayList<ScheduledAppointment> getAvailableAppointments(Doctor doctor) {
        ArrayList<ScheduledAppointment> availableAppointments = new ArrayList<ScheduledAppointment>();

        System.out.println("Found appointments");
        return availableAppointments;
    }

    /**
     * Finds the available appointments of any doctor
     * of the given specialty
     * @return ArrayList of type Appointment
     */
    public ArrayList<ScheduledAppointment> getAvailableAppointments(String specialty) {
        ArrayList<ScheduledAppointment> availableAppointments = new ArrayList<>();

        System.out.println("Found appointments");
        return availableAppointments;
    }

    /**
     * Adds this doctor to the database
     * @param connection A connection to the database
     */
    @Override
    public void addToDatabase(Connection connection) {
        try {
            Query query = Query.addPatient(connection);
            query.getStatement().setString(1, amka);
            query.getStatement().setString(2, username);
            query.getStatement().setString(3, hashedPassword);
            query.getStatement().setString(4, firstName);
            query.getStatement().setString(5, surname);
            query.getStatement().setString(6, email);
            query.getStatement().setString(7, phoneNumber);

            query.getStatement().execute();

            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to add a patient to the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes this patient from the database
     * @param connection A connection to the database
     */
    @Override
    public void removeFromDatabase(Connection connection) {
        try {
            Query query = Query.removePatient(connection, amka);
            query.getStatement().execute();
            System.out.println("Successfully deleted patient with amka " + amka + " from the database");

            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting a patient from the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Search for a patient in the database
     * @param query : The query that determines by which field the patient will be selected
     * @return A patient created from the data provided by the database, or null if he doesn't exist
     */
    public static Patient getFromDatabase(Query query) {
        try {
            ResultSet resultSet = query.getStatement().executeQuery();

            Patient patient = null;
            if (resultSet.next()) {
                patient = new Patient(resultSet);
            }

            query.getStatement().close();
            return patient;
        } catch (SQLException e) {
            System.out.println("An error occurred while getting a patient from the database");
            System.out.println(e.getMessage());
            return null;
        }
    }

}

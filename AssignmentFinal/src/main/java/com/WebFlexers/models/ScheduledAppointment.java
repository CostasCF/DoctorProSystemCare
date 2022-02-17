package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ScheduledAppointment implements IDatabaseSupport {

    private String appointmentID;
    private String patientAmka;
    private String doctorAmka;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    // Constructors
    public ScheduledAppointment(ResultSet resultSet)
    {
        try
        {
            DatabaseManager databaseManager = new DatabaseManager();
            Connection connection = databaseManager.getConnection();

            appointmentID = resultSet.getString("appointment_id");
            doctorAmka = resultSet.getString("doctor_amka");
            patientAmka = resultSet.getString("patient_amka");
            date = resultSet.getDate("date").toLocalDate();
            startTime = resultSet.getTime("start_time").toLocalTime();
            endTime = resultSet.getTime("end_time").toLocalTime();

            databaseManager.closeConnection();
        }
        catch (SQLException ex)
        {
            System.out.println("An error occurred while creating scheduled appointment from result set");
            System.out.println(ex.toString());
        }
    }

    public ScheduledAppointment(String appointmentID, String patientAmka, String doctorAmka, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.appointmentID = appointmentID;
        this.patientAmka = patientAmka;
        this.doctorAmka = doctorAmka;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public String getAppointmentID() { return appointmentID; }

    public void setAppointmentID(String appointmentID) { this.appointmentID = appointmentID; }

    public String getPatientAmka() { return patientAmka; }

    public void setPatientAmka(String patientAmka) { this.patientAmka = patientAmka; }

    public String getDoctorAmka() { return doctorAmka; }

    public void setDoctorAmka(String doctorAmka) { this.doctorAmka = doctorAmka; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getStartTime() { return startTime; }

    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }

    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    // Database related methods
    /**
     * Adds this scheduled appointment to the database
     * @param connection A connection to the database
     */
    @Override
    public void addToDatabase(Connection connection) {
        try {
            Query query = Query.addScheduledAppointment(connection);
            query.getStatement().setString(1, appointmentID);
            query.getStatement().setString(2, doctorAmka);
            query.getStatement().setString(3, patientAmka);
            query.getStatement().setDate(4, Date.valueOf(date));
            query.getStatement().setTime(5, Time.valueOf(startTime));
            query.getStatement().setTime(6, Time.valueOf(endTime));

            query.getStatement().execute();

            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to add a scheduled appointment to the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes a scheduled appointment from the database
     * @param connection A connection to the database
     */
    @Override
    public void removeFromDatabase(Connection connection) {
        try {
            Query query = Query.removeScheduledAppointment(connection, appointmentID);
            query.getStatement().execute();
            System.out.println("Successfully deleted scheduled appointment with id " + appointmentID + " from the database");

            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting a scheduled appointment from the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Search for an available appointment in the database
     * @param query : The query that determines by which field the appointment will be selected
     * @return An available appointment created from the data provided by the database, or null if he doesn't exist
     */
    public static ScheduledAppointment getFromDatabase(Query query) {
        try {
            ResultSet resultSet = query.getStatement().executeQuery();

            ScheduledAppointment appointment = null;
            if (resultSet.next()) {
                appointment = new ScheduledAppointment(resultSet);
            }

            query.getStatement().close();
            return appointment;
        } catch (SQLException e) {
            System.out.println("An error occurred while getting a Scheduled Appointment from the database");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Gets all the scheduled appointments from the database
     * @param query : The query that returns all the scheduled appointments from the database
     * @return An ArrayList of type ScheduledAppointment or null if none exists
     */
    public static ArrayList<ScheduledAppointment> getMultipleFromDatabase(Query query) {

        try {
            ResultSet resultSet = query.getStatement().executeQuery();
            ArrayList<ScheduledAppointment> appointments = new ArrayList<>();

            // Add all the appointments to an ArrayList
            while (resultSet.next()) {
                appointments.add(new ScheduledAppointment(resultSet));
            }

            // Close the connection
            query.getStatement().close();

            // Return the ArrayList of appointments or null if no appointments are found
            if (appointments.isEmpty())
                return null;
            else
                return appointments;
        } catch (SQLException e) {
            System.out.println("An error occurred while getting all doctors from the database");
            return null;
        }
    }
}

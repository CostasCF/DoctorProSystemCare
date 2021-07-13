package com.WebFlexers.models;

import com.WebFlexers.PasswordAuthentication;
import com.WebFlexers.Query;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AvailableAppointment implements IDatabaseSupport {
    // Attributes
    private final String appointment_id;
    private String doctorAmka;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    // Constructors

    /**
     * Creates an available appointment from a database result set
     * @param resultSet
     */
    public AvailableAppointment(ResultSet resultSet) {
        String tempID = null;
        try {
            tempID = resultSet.getString(1);
            doctorAmka = resultSet.getString(2);
            date = resultSet.getDate(3).toLocalDate();
            startTime = resultSet.getTime(4).toLocalTime();
            endTime = resultSet.getTime(5).toLocalTime();
        } catch (SQLException e) {
            System.out.println("An error occurred while creating available appointment from result set");
            System.out.println(e.getMessage());
        }

        appointment_id = tempID;
    }

    public AvailableAppointment(String appointment_id, String doctorAmka, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.appointment_id = appointment_id;
        this.doctorAmka = doctorAmka;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and setters
    public String getAppointment_id() { return appointment_id; }

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
     * Adds an available appointment to the database
     * @param query The query that adds an available appointment to the database
     */
    @Override
    public void addToDatabase(Query query) {
        try {
            query.getStatement().setString(1, appointment_id);
            query.getStatement().setString(2, doctorAmka);
            query.getStatement().setDate(3, Date.valueOf(date));
            query.getStatement().setTime(4, Time.valueOf(startTime));
            query.getStatement().setTime(5, Time.valueOf(endTime));

            query.getStatement().execute();
            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to add an available appointment to the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes an available appointment from the database
     * @param query The query that removes an available appointment to the database
     */
    @Override
    public void removeFromDatabase(Query query) {
        try {
            query.getStatement().setString(1, appointment_id);
            query.getStatement().execute();
            System.out.println("Successfully deleted available appointment with id " + appointment_id + " from the database");

            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting an available appointment from the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Search for an available appointment in the database
     * @param query : The query that determines by which field the appointment will be selected
     * @return An available appointment created from the data provided by the database, or null if he doesn't exist
     */
    public static AvailableAppointment getFromDatabase(Query query) {
        try {
            ResultSet resultSet = query.getStatement().executeQuery();

            AvailableAppointment appointment = null;
            if (resultSet.next()) {
                appointment = new AvailableAppointment(resultSet);
            }

            query.getStatement().close();
            return appointment;
        } catch (SQLException e) {
            System.out.println("An error occurred while getting an available appointment from the database");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Gets all the available appointments from the database
     * @param query : The query that returns all the available appointments from the database
     * @return An ArrayList of type AvailableAppointment or null if no Available Appointments are found
     */
    public ArrayList<AvailableAppointment> getAllFromDatabase(Query query) {

        try {
            ResultSet resultSet = query.getStatement().executeQuery();
            ArrayList<AvailableAppointment> appointments = new ArrayList<>();

            // Add all the appointments to an ArrayList
            while (resultSet.next()) {
                appointments.add(new AvailableAppointment(resultSet));
            }

            // Close the connection
            query.getStatement().close();

            // Return the ArrayList of appointments or null if no appointments are found
            if (appointments.isEmpty())
                return null;
            else
                return appointments;
        } catch (SQLException e) {
            System.out.println("An error occurred while getting all available appointments from the database");
            return null;
        }
    }
}

package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;

import javax.print.Doc;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScheduledAppointment implements IDatabaseSupport {

    private String appointment_id;
    private Patient patient;
    private Doctor doctor;
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

            appointment_id = resultSet.getString("appointment_id");
            doctor = Doctor.getFromDatabase(Query.getDoctorByAmka(connection, resultSet.getString("doctor_amka")));
            patient = Patient.getFromDatabase(Query.getPatientByAmka(connection, resultSet.getString("patient_amka")));
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

    public ScheduledAppointment(Patient patient, Doctor doctor, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates an open appointment (without a specified doctor and patient)
     * @param appointment_id : The appointment id
     * @param date : The date of the appointment
     * @param startTime : The start time of the appointment
     * @param endTime : The end time of the appointment
     */
    public ScheduledAppointment(String appointment_id, String date, String startTime, String endTime ) {
        this.appointment_id = appointment_id;
        this.startTime =  LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
        this.date= LocalDate.parse(date);
    }

    // Getters and Setters
    public String getAppointment_id() { return appointment_id; }

    public void setAppointment_id(String appointment_id) { this.appointment_id = appointment_id; }

    public Patient getPatient() { return patient; }

    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

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
            query.getStatement().setString(1, appointment_id);
            query.getStatement().setString(2, doctor.getAmka());
            query.getStatement().setString(3, patient.getAmka());
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
            Query query = Query.removeScheduledAppointment(connection, appointment_id);
            query.getStatement().execute();
            System.out.println("Successfully deleted scheduled appointment with id " + appointment_id + " from the database");

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
    public static ArrayList<ScheduledAppointment> getAllFromDatabase(Query query) {

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

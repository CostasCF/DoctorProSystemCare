package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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

            appointment_id = resultSet.getString(1);
            doctor = databaseManager.getDoctorByAmka(resultSet.getString(2));
            patient = databaseManager.getPatientByAmka(resultSet.getString(3));

            //Converting string to Local Date Time
            String tempDate = resultSet.getString(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(tempDate, formatter);

            startTime = LocalTime.parse(resultSet.getString(5));
            endTime = LocalTime.parse(resultSet.getString(6));
        }
        catch (SQLException ex)
        {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }
    }

    public ScheduledAppointment(Patient patient, Doctor doctor, LocalDate date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
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
     * @param query The query that will be used to add the scheduled appointment to the database
     */
    @Override
    public void addToDatabase(Query query) {
        try {
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
     * @param query The query that removes a scheduled appointment from the database
     */
    @Override
    public void removeFromDatabase(Query query) {
        try {
            query.getStatement().setString(1, appointment_id);
            query.getStatement().execute();
            System.out.println("Successfully deleted scheduled appointment with id " + appointment_id + " from the database");

            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting a scheduled appointment from the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets all the scheduled appointments from the database
     * @param query : The query that returns all the scheduled appointments from the database
     * @return An ArrayList of type ScheduledAppointment or null if none exists
     */
    public ArrayList<ScheduledAppointment> getAllFromDatabase(Query query) {

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

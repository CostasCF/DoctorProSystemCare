package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {

    private String appointment_id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;

    // Constructors
    public Appointment(ResultSet resultSet)
    {
        try
        {
            DatabaseManager databaseManager = new DatabaseManager();

            appointment_id = resultSet.getString(1);
            doctor = databaseManager.getDoctorByAmka(resultSet.getString(2));
            patient = databaseManager.getPatientByAmka(resultSet.getString(3));

            //Converting string to Local Date Time
            String str = resultSet.getString(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateTime = LocalDateTime.parse(str, formatter);
        }
        catch (SQLException ex)
        {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }
    }

    public Appointment(Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
    }


    // Getters and setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

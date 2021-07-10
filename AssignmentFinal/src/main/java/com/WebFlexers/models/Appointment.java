package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Appointment {

    private String appointment_id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate dateTime;
    private String start_time;
    private String end_time;

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
            dateTime = LocalDate.parse(str, formatter);

            start_time = resultSet.getString(5);
            end_time = resultSet.getString(6);
        }
        catch (SQLException ex)
        {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }
    }

    public Appointment(Patient patient, Doctor doctor, LocalDate dateTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
    }


    public String getAppointment_id() { return appointment_id; }

    public void setAppointment_id(String appointment_id) { this.appointment_id = appointment_id; }

    public Patient getPatient() { return patient; }

    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public LocalDate getDateTime() { return dateTime; }

    public void setDateTime(LocalDate dateTime) { this.dateTime = dateTime; }

    public String getStart_time() { return start_time; }

    public void setStart_time(String start_time) { this.start_time = start_time; }

    public String getEnd_time() { return end_time; }

    public void setEnd_time(String end_time) { this.end_time = end_time; }
}

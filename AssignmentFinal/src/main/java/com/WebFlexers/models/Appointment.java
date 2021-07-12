package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Appointment {

    private String appointment_id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private LocalTime start_time;
    private LocalTime end_time;

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
            String tempDate = resultSet.getString(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(tempDate, formatter);

            start_time = LocalTime.parse(resultSet.getString(5));
            end_time = LocalTime.parse(resultSet.getString(6));
        }
        catch (SQLException ex)
        {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }
    }

    public Appointment(Patient patient, Doctor doctor, LocalDate date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }


    public String getAppointment_id() { return appointment_id; }

    public void setAppointment_id(String appointment_id) { this.appointment_id = appointment_id; }

    public Patient getPatient() { return patient; }

    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getStart_time() { return start_time; }

    public void setStart_time(LocalTime start_time) { this.start_time = start_time; }

    public LocalTime getEnd_time() { return end_time; }

    public void setEnd_time(LocalTime end_time) { this.end_time = end_time; }
}

package com.WebFlexers.models;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class Doctor extends User {

    private List<String> schedule;
    private LocalDateTime dateTime;
    private String amka;
    private String email;
    private String phoneNum;

    private String adminID;
    private final String specialty;

    // Getters
    public String getSpecialty() { return specialty; }
    public String getAmka() { return amka; }
    public String getEmail() { return email; }
    public String getPhoneNum() { return phoneNum; }
    public String getAdminID() { return adminID; }

    // Setters
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }
    public void setAdminID(String adminID) { this.adminID = adminID; }
    /**
     * A doctor that is instantiated with data from a database
     * @param resultSet : The data from the database
     */
    public Doctor(ResultSet resultSet) {
        String specialtyTemp = null;

        try {
            amka = resultSet.getString(1);
            setUsername(resultSet.getString(2));
            setPassword(resultSet.getString(3));
            setFirstName(resultSet.getString(4));
            setSurname(resultSet.getString(5));
            specialtyTemp = (resultSet.getString(6));
            setEmail(resultSet.getString(7));
            setPhoneNum(resultSet.getString(8));
            setAdminID(resultSet.getString(9));

        } catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }

        specialty = specialtyTemp;
    }

    public Doctor(String amka, String username, String password, String name, String surname, String specialty,String phoneNum,String email, String adminID) {
        super(username, password, name, surname);
        this.amka = amka;
        this.specialty = specialty;
        this.phoneNum = phoneNum;
        this.adminID = adminID;
        this.email = email;
    }


    /**
     * Doctor inserts the date he is available to check in patients
     */
    public void insertDateAvailability(String doctorAvailability)
    {
        dateTime = LocalDateTime.now();
        System.out.println("Available date added");
    }

    /**
     * View doctor's appointment availability
     */
    public void viewAppointmentAvailability()
    {
        System.out.println("The doctor is available 24/7");
    }

    /**
     * Cancel patient's appointment only if it's scheduled at least 3 days later compared to the current date.
     */
    public void cancelAppointment(Appointment appointmentToBeCancelled)
    {
        System.out.println("Appointment Cancelled");
    }

}

package com.WebFlexers.models;

import java.sql.*;
import java.util.ArrayList;

public class Patient extends User {

    private String amka;
    private String username;
    private String firstname;
    private String surname;
    private String email;
    private String phone_num;
    private ArrayList<Appointment> scheduledAppointments = new ArrayList<>();

    /**
     * A patient that is instantiated with data from a database
     * @param resultSet : The data from the database
     */
    public Patient(ResultSet resultSet) {
        String tempAmka = "";
        try {
            tempAmka = resultSet.getString(1);
            username = resultSet.getString(2);
            firstname = resultSet.getString(4);
            surname = resultSet.getString(5);
            email = resultSet.getString(6);
            phone_num = resultSet.getString(7);
            //scheduledAppointments = appointments;
        } catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println("test 3-4?");
        }
        amka = tempAmka;
    }

    public Patient(String amka, String username, String password, String firstname, String surname, String email,
                   String phone_num) {
        super(username, password, firstname, surname);
        this.amka = amka;
        this.phone_num = phone_num;
        this.email= email;
    }
    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public ArrayList<Appointment> getScheduledAppointments() {
        return scheduledAppointments;
    }

    public void setScheduledAppointments(ArrayList<Appointment> scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }

    /**
     * Adds an appointment to the schedule
     * @param appointment: The scheduled appointment between the doctor and the patient
     */
    public void addAppointment(Appointment appointment) {
        scheduledAppointments.add(appointment);
    }

    /**
     * Removes appointment from the list of scheduled appointments
     * @param appointment: The scheduled appointment between the doctor and the patient
     */
    public void cancelAppointment(Appointment appointment) {
        scheduledAppointments.remove(appointment);
    }

    /**
     * Replaces the old appointment with the new one in the scheduled appointments list
     */
    public void replaceAppointment(Appointment oldAppointment, Appointment newAppointment) {
        if (scheduledAppointments.contains(oldAppointment)) {
            scheduledAppointments.set(scheduledAppointments.indexOf(oldAppointment), newAppointment);
        }
    }
}

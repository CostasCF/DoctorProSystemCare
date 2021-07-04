package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Patient extends User {

    private ArrayList<Appointment> scheduledAppointments = new ArrayList<>();

    private final String amka;
    private String phoneNumber, email;

    // Getters and setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAmka() {
        return amka;
    }

    public ArrayList<Appointment> getScheduledAppointments() {
        return scheduledAppointments;
    }

    public void setScheduledAppointments(ArrayList<Appointment> scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }

    /**
     * A patient that is instantiated with data from a database
     * @param resultSet : The data from the database
     */
    public Patient(ResultSet resultSet) {
        String tempAmka = "";
        try {
            tempAmka = resultSet.getString(1);
            setUsername(resultSet.getString(2));
            setName(resultSet.getString(4));
            setSurname(resultSet.getString(5));
            email = resultSet.getString(6);
            phoneNumber = resultSet.getString(7);
        } catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
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
    public ArrayList<Appointment> getAvailableAppointments(Doctor doctor) {
        ArrayList<Appointment> availableAppointments = new ArrayList<Appointment>();

        System.out.println("Found appointments");
        return availableAppointments;
    }

    /**
     * Finds the available appointments of any doctor
     * of the given specialty
     * @return ArrayList of type Appointment
     */
    public ArrayList<Appointment> getAvailableAppointments(String specialty) {
        ArrayList<Appointment> availableAppointments = new ArrayList<>();

        System.out.println("Found appointments");
        return availableAppointments;
    }

    /**
     * Prints out the scheduled appointments
     */
  /*  public void viewScheduledAppointments() {
        for (var appointment : scheduledAppointments) {
            System.out.println(appointment);
        }
    }
*/

    // Ignore this for now
    /**
     * Gets patient's appointment history from the database
     */
    /*public static ArrayList<Appointment> getAppointmentsHistory(String amka) {

        try{
            Connection connection = DatabaseManager.Connect();

            while(resultSet.next())
            {
                String[] temp = {
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                };
            }

            return appointment_details;
        }
        catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }
        return appointment_details;
    }*/

}

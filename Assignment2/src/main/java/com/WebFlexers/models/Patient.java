package com.WebFlexers.models;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Patient extends Users {

    //private final String amka;
    private ArrayList<Appointment> scheduledAppointments = new ArrayList<>();

  //  public Patient(String username, String password, String name, String surname, String amka) {
        //super(username, password, name, surname);
     //   this.amka = amka;
 //   }

  public String amka ,phoneNumber, firstname,surname, email;

    public Patient(String amka,  String phoneNumber, String firstname, String surname, String email){
        this.amka = amka;
        this.phoneNumber = phoneNumber;
        this.firstname = firstname;
        this.surname = surname;
        this.email= email;
    }

    public String getAmka() {
       return amka;
   }

    public ArrayList<Appointment> getScheduledAppointments() {
        return scheduledAppointments;
    }

    /**
     * Sets the appointment schedule
     * @param scheduledAppointments: Array list with the appointments
     */
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

    /**
     * Registers a new user to the database
     */
    public void registerUser() {
        System.out.println("User registered");
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
    /**
     *  Gets the patient's details from the database
     */
    public static Patient viewPatientDetails(String amka) {
        String  phoneNumber= "", firstname= "",surname= "", email= "";
        String url = "jdbc:postgresql://ec2-52-209-134-160.eu-west-1.compute.amazonaws.com:5432/d35afkue7kt3ri";
        Properties props = new Properties();
        props.setProperty("user","dmupmilluzwkvw");
        props.setProperty("password","1f80c2791969210ee5777c436e20ee52ca006ee7f1c2dbfaf86baa32f976f2fa");
        Patient _patient = new Patient("","","","","");
        try{
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connected Successfully to the database");

            PreparedStatement preparedStatement = conn.prepareStatement("select * from patient (amka,firstname,surname,email,phone_num)  values (?, ?, ?,?,?)");

            preparedStatement.setString(1, amka);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phoneNumber);

            System.out.println(amka);
            ResultSet resultSet = preparedStatement.executeQuery();
           // return resultSet.next();
             _patient = new Patient(amka,phoneNumber,firstname,surname,email);
            return _patient;
        }
        catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }
        return _patient;
    }




    /**
     * Prints out the appointment history
     */
//    public static boolean viewAppointmentHistory(String amka, String appointmentId, String doctor, String date, String startTime, String endTime, String phoneNumber) {
//        String url = "jdbc:postgresql://ec2-52-209-134-160.eu-west-1.compute.amazonaws.com:5432/d35afkue7kt3ri";
//        Properties props = new Properties();
//        props.setProperty("user","dmupmilluzwkvw");
//        props.setProperty("password","1f80c2791969210ee5777c436e20ee52ca006ee7f1c2dbfaf86baa32f976f2fa");
//
//        try{
//            Connection conn = DriverManager.getConnection(url, props);
//            System.out.println("Connected Successfully to the database");
//
//            PreparedStatement preparedStatement = conn.prepareStatement("select * from patient ");
//
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return resultSet.next();
//
//        }
//        catch (SQLException ex) {
//            System.out.println("An error occured while connecting to database");
//            System.out.println(ex.toString());
//        }
//        return false;
//    }

    /**
     * Validates the patient user in the database
     */
    public static boolean validatePatient(String username, String password) throws SQLException {

        String url = "jdbc:postgresql://ec2-52-209-134-160.eu-west-1.compute.amazonaws.com:5432/d35afkue7kt3ri";
        Properties props = new Properties();
        props.setProperty("user","dmupmilluzwkvw");
        props.setProperty("password","1f80c2791969210ee5777c436e20ee52ca006ee7f1c2dbfaf86baa32f976f2fa");

        try{
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connected Successfully to the database");

            PreparedStatement preparedStatement = conn.prepareStatement("select * from patient where username=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        }
        catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }

        return false;
    }



}

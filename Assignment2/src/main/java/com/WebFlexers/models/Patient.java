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
    public static String[] getPatientDetails(String username) {

        String details[] = new String[7];

        //Connecting to database
        String url = "jdbc:postgresql://ec2-52-209-134-160.eu-west-1.compute.amazonaws.com:5432/d35afkue7kt3ri";
        Properties props = new Properties();
        props.setProperty("user","dmupmilluzwkvw");
        props.setProperty("password","1f80c2791969210ee5777c436e20ee52ca006ee7f1c2dbfaf86baa32f976f2fa");

        try{
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connected Successfully to the database");

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM patient WHERE username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            for(int i = 1; i<=7; i++)
                details[i-1] = resultSet.getString(i);

            conn.close();

            return details;
        }
        catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }
        return details;
    }




    /**
     * Gets patient's appointment history from the database
     */
    public static ArrayList<String[]> getAppointmentsHistory(String amka) {
        ArrayList<String[]> appointment_details = new ArrayList<String[]>();

        //Connecting to database
        String url = "jdbc:postgresql://ec2-52-209-134-160.eu-west-1.compute.amazonaws.com:5432/d35afkue7kt3ri";
        Properties props = new Properties();
        props.setProperty("user","dmupmilluzwkvw");
        props.setProperty("password","1f80c2791969210ee5777c436e20ee52ca006ee7f1c2dbfaf86baa32f976f2fa");

        try{
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connected Successfully to the database");

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM appointment WHERE patient=?");
            statement.setString(1, amka);

            ResultSet resultSet = statement.executeQuery();
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
                appointment_details.add(temp);
            }
            conn.close();
            return appointment_details;
        }
        catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }
        return appointment_details;
    }

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
            conn.close();

            return resultSet.next();
        }
        catch (SQLException ex) {
            System.out.println("An error occured while connecting to database");
            System.out.println(ex.toString());
        }

        return false;
    }



}

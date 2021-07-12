package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.PasswordAuthentication;
import com.WebFlexers.Query;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends User implements IDatabaseSupport {



    public static ArrayList<Appointment> ScheduledAppointments;
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
    public static ArrayList<Appointment> getScheduledAppointments() { return ScheduledAppointments; }


    // Setters
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }
    public void setAdminID(String adminID) { this.adminID = adminID; }
    public static void setScheduledAppointments(ArrayList<Appointment> getScheduledAppointments) { ScheduledAppointments = getScheduledAppointments; }
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

    public static void viewScheduledAppointments(HttpServletRequest request, String doctor_amka, DatabaseManager database){
        try{
            ArrayList<Appointment> appointments;
            appointments = database.getScheduledAppointmentsByDoctorAMKA(doctor_amka);
            request.setAttribute("listAppointments", appointments);
            setScheduledAppointments(appointments);
        }catch (Exception e){
            System.out.println("Problem with fetching  doctors' appointments : " + e.getMessage());
        }
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

    // Database related methods

    /**
     * Adds this doctor to the database
     * @param query The query that will be used to add the doctor to the database
     */
    @Override
    public void addToDatabase(Query query) {
        try {
            query.getStatement().setString(1, amka);
            query.getStatement().setString(2, username);

            PasswordAuthentication cryptography = new PasswordAuthentication();
            String hashedPassword = cryptography.hash(password.toCharArray());
            query.getStatement().setString(3, hashedPassword);

            query.getStatement().setString(4, firstName);
            query.getStatement().setString(5, surname);
            query.getStatement().setString(6, specialty);
            query.getStatement().setString(7, email);
            query.getStatement().setString(8, phoneNum);
            query.getStatement().setString(9, adminID);

            query.getStatement().execute();

            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occured while trying to add a doctor to the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes this doctor from the database
     * @param query The query to remove the doctor from the database
     */
    @Override
    public void removeFromDatabase(Query query) {
        try {
            query.getStatement().setString(1, amka);
            query.getStatement().execute();
            System.out.println("Successfully deleted doctor with amka " + amka + " from the database");

            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting a doctor from the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Search for a doctor in the database
     * @param query : The query that determines by which field the doctor will be selected
     * @return A doctor created from the data provided by the database, or null if he doesn't exist
     */
    public static Doctor getFromDatabase(Query query) {
        try {
            ResultSet resultSet = query.getStatement().executeQuery();

            if (resultSet.next()) {
                return new Doctor(resultSet);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while getting an admin from the database");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Returns a doctor from the database
     * @param query : The query that returns all the doctors from the database
     * @return A doctor created from the data provided by the database
     */
    public ArrayList<Doctor> getAllFromDatabase(Query query) {

        try {
            ResultSet resultSet = query.getStatement().executeQuery();
            ArrayList<Doctor> doctors = new ArrayList<>();

            while (resultSet.next()) {
                doctors.add(new Doctor(resultSet));
            }

            return doctors;
        } catch (SQLException e) {
            System.out.println("An error occured while getting all doctors from the database");
            return null;
        }
    }
}

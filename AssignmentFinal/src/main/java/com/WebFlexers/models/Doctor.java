package com.WebFlexers.models;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.PasswordAuthentication;
import com.WebFlexers.Query;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Doctor extends User implements IDatabaseSupport {

    // Attributes
    private String amka;
    private String email;
    private String phoneNum;
    private String adminID;
    private final String specialty;

    // Constructors
    /**
     * A doctor that is instantiated with data from a database
     * @param resultSet : The data from the database
     */
    public Doctor(ResultSet resultSet) {
        String specialtyTemp = null;

        try {
            amka = resultSet.getString("amka");
            username = resultSet.getString("username");
            hashedPassword = resultSet.getString("password");
            firstName = resultSet.getString("first_name");
            surname = resultSet.getString("last_name");
            specialtyTemp = resultSet.getString("speciality");
            email = resultSet.getString("email");
            phoneNum = resultSet.getString("phone_num");
            adminID = resultSet.getString("admin_id");

        } catch (SQLException ex) {
            System.out.println("An error occurred while creating a doctor from ResultSet");
            System.out.println(ex.toString());
        }

        specialty = specialtyTemp;
    }

    public Doctor(String amka, String username, String password, String firstname, String surname, String specialty,String phoneNum,String email, String adminID) {
        super(username, password, firstname, surname);
        this.amka = amka;
        this.specialty = specialty;
        this.phoneNum = phoneNum;
        this.adminID = adminID;
        this.email = email;
    }

    // Getters and Setters
    public String getAmka() { return amka; }

    public void setAmka(String amka) { this.amka = amka; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNum() { return phoneNum; }

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getAdminID() { return adminID; }

    public String getSpecialty() { return specialty; }


    // Database related methods
    /**
     * Adds this doctor to the database
     * @param connection A connection to the database
     */
    @Override
    public void addToDatabase(Connection connection) {
        try {
            Query query = Query.addDoctor(connection);
            query.getStatement().setString(1, amka);
            query.getStatement().setString(2, username);
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
            System.out.println("An error occurred while trying to add a doctor to the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes this doctor from the database
     * @param connection A connection to the database
     */
    @Override
    public void removeFromDatabase(Connection connection) {
        try {
            Query query = Query.removeDoctor(connection, amka);
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

            Doctor doctor = null;
            if (resultSet.next()) {
                doctor = new Doctor(resultSet);
            }

            query.getStatement().close();
            return doctor;
        } catch (SQLException e) {
            System.out.println("An error occurred while getting a doctor from the database");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Gets all the doctors from the database
     * @param query : The query that returns all the doctors from the database
     * @return An ArrayList of type doctor or null if no doctors are found
     */
    public ArrayList<Doctor> getMultipleFromDatabase(Query query) {

        try {
            ResultSet resultSet = query.getStatement().executeQuery();
            ArrayList<Doctor> doctors = new ArrayList<>();

            // Add all the doctors to an ArrayList
            while (resultSet.next()) {
                doctors.add(new Doctor(resultSet));
            }

            // Close the connection
            query.getStatement().close();

            // Return the ArrayList of doctors or null if no doctors are found
            if (doctors.isEmpty())
                return null;
            else
                return doctors;
        } catch (SQLException e) {
            System.out.println("An error occurred while getting all doctors from the database");
            return null;
        }
    }

    /**
     * Creates a list of this doctor's appointments that are scheduled into the future
     * @return An ArrayList of type ScheduledAppointment or null if no future appointments exist
     */
    public ArrayList<ScheduledAppointment> getScheduledAppointments(Connection connection) {
        ArrayList<ScheduledAppointment> scheduledAppointments = new ArrayList<>();
        try {
            // First get all the appointments of this doctor
            ArrayList<ScheduledAppointment> allAppointments = ScheduledAppointment.getMultipleFromDatabase (
                    Query.getScheduledAppointmentsByDoctorAmka(connection, amka));

            if (allAppointments != null) {
                // Add to the list of appointments every one of them whose date is later than today's
                for (var appointment : allAppointments) {
                    LocalDateTime appointmentDateTime = LocalDateTime.of(appointment.getDate(), appointment.getStartTime());
                    if (appointmentDateTime.compareTo(LocalDateTime.now()) > 0) {
                        scheduledAppointments.add(appointment);
                    }
                }

                // Return null if no scheduled appointments exist
                if (scheduledAppointments.isEmpty()) {
                    return null;
                }

                // Return the list of scheduled appointments if any are found
                return scheduledAppointments;
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while trying to get scheduled appointments from the database");
            System.out.println(e.getMessage());
        }
        return  scheduledAppointments;
    }
}

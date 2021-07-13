package com.WebFlexers.models;

import com.WebFlexers.Query;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

public class Patient extends User implements IDatabaseSupport {

    private final String amka;
    private String phoneNumber;
    private String email;

    public static String message;
    /**
     * A patient that is instantiated with data from a database
     * @param resultSet : The data from the database
     */
    public Patient(ResultSet resultSet) {
        String tempAmka = "";
        try {
            tempAmka = resultSet.getString("amka");
            username = resultSet.getString("username");
            hashedPassword = resultSet.getString("password");
            firstName = resultSet.getString("first_name");
            surname = resultSet.getString("last_name");
            email = resultSet.getString("email");
            phoneNumber = resultSet.getString("phone_num");
        } catch (SQLException ex) {
            System.out.println("An error occurred while creating patient from resultSet");
            System.out.println(ex.getMessage());
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

    // Getters and setters
    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getAmka() { return amka; }

    public static void setMessage(String message) { Patient.message = message; }

    public static String getMessage() { return message; }

    /**
     * Creates a list of all the previous appointments of this patient
     * @return An ArrayList of type ScheduledAppointment or null if no old appointments exist
     */
    public ArrayList<ScheduledAppointment> getOldAppointments(Connection connection) {
        try {
            // First get all the appointments of this patient
            ArrayList<ScheduledAppointment> allAppointments = ScheduledAppointment.getMultipleFromDatabase(
                    Query.getScheduledAppointmentsByPatientAmka(connection, amka));

            if (allAppointments != null) {
                System.out.println(allAppointments.size());

                ArrayList<ScheduledAppointment> oldAppointments = new ArrayList<>();
                // Add to the list of old appointments every one of them whose date is less than today's
                for (var appointment : allAppointments) {
                    LocalDateTime appointmentDateTime = LocalDateTime.of(appointment.getDate(), appointment.getStartTime());
                    if (appointmentDateTime.compareTo(LocalDateTime.now()) < 0) {
                        oldAppointments.add(appointment);
                    }
                }

                // Return null if no old appointments exist
                if (oldAppointments.isEmpty()) {
                    return null;
                }

                // Return the list of old appointments if any are found
                return oldAppointments;
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while trying to get old appointments from the database");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Creates a list of this patient's appointments that are scheduled into the future
     * @return An ArrayList of type ScheduledAppointment or null if no future appointments exist
     */
    public ArrayList<ScheduledAppointment> getScheduledAppointments(Connection connection) {
        ArrayList<ScheduledAppointment> scheduledAppointments = new ArrayList<>();
        try {
            // First get all the appointments of this patient
            ArrayList<ScheduledAppointment> allAppointments = ScheduledAppointment.getMultipleFromDatabase (
                    Query.getScheduledAppointmentsByPatientAmka(connection, amka));

            if (allAppointments != null) {
                // Add to the list of old appointments every one of them whose date is less than today's
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

    /**
     * Cancels this appointment if it is more than 3 days away and deletes it from the database
     * @param connection A connection to the database
     * @return True if the appointment got cancelled, false otherwise
     */
    public void cancelAppointment(Connection connection, ScheduledAppointment appointment) {
        // Get the date and time of the appointment and compare them with now
        LocalDateTime appointmentDateTime = LocalDateTime.of(appointment.getDate(), appointment.getStartTime());
        System.out.println("Number of days: " + DAYS.between(LocalDateTime.now(), appointmentDateTime));
        if (DAYS.between(LocalDateTime.now(), appointmentDateTime) > 3) {
            appointment.removeFromDatabase(connection);
            message = "Successfully deleted appointment with ID " + appointment.getAppointmentID();
            System.out.println("Successfully deleted appointment with ID " + appointment.getAppointmentID());
        }
        else {
            message = "Couldn't delete appointment with ID " + appointment.getAppointmentID() + ", because " +
                     "it is scheduled for less than 3 days from now";
            System.out.println("Couldn't delete appointment with ID " + appointment.getAppointmentID() + ", because " +
                    "it is scheduled for less than 3 days from now");
        }
    }

    /**
     * Adds this patient to the database
     * @param connection A connection to the database
     */
    @Override
    public void addToDatabase(Connection connection) {
        try {
            Query query = Query.addPatient(connection);
            query.getStatement().setString(1, amka);
            query.getStatement().setString(2, username);
            query.getStatement().setString(3, hashedPassword);
            query.getStatement().setString(4, firstName);
            query.getStatement().setString(5, surname);
            query.getStatement().setString(6, email);
            query.getStatement().setString(7, phoneNumber);

            query.getStatement().execute();

            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to add a patient to the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes this patient from the database
     * @param connection A connection to the database
     */
    @Override
    public void removeFromDatabase(Connection connection) {
        try {
            Query query = Query.removePatient(connection, amka);
            query.getStatement().execute();
            System.out.println("Successfully deleted patient with amka " + amka + " from the database");

            query.getStatement().close();
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting a patient from the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Search for a patient in the database
     * @param query : The query that determines by which field the patient will be selected
     * @return A patient created from the data provided by the database, or null if he doesn't exist
     */
    public static Patient getFromDatabase(Query query) {
        try {
            ResultSet resultSet = query.getStatement().executeQuery();

            Patient patient = null;
            if (resultSet.next()) {
                patient = new Patient(resultSet);
            }

            query.getStatement().close();
            return patient;
        } catch (SQLException e) {
            System.out.println("An error occurred while getting a patient from the database");
            System.out.println(e.getMessage());
            return null;
        }
    }

}

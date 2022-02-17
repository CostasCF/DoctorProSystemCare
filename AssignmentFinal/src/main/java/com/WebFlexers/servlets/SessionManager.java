package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.models.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SessionManager {
    public static void prepareAdminSession(Admin admin, HttpSession session) {
        session.setAttribute("user", admin);

        try {
            DatabaseManager dbManager = new DatabaseManager();
            session.setAttribute("allDoctors", Doctor.getMultipleFromDatabase(Query.getAllDoctors(dbManager.getConnection())));
            dbManager.closeConnection();
        } catch (SQLException e) {
            System.out.println("An error occurred while getting all admins from the database");
            System.out.println(e.getMessage());
        }

    }

    public static void preparePatientSession(Patient patient, HttpSession session) {
        // Store the patient to a session
        session.setAttribute("user", patient);

        // Get the scheduled and available appointments from the database
        DatabaseManager dbManager = new DatabaseManager();
        session.setAttribute("scheduledAppointments", patient.getScheduledAppointments(dbManager.getConnection()));
        session.setAttribute("oldAppointments", patient.getOldAppointments(dbManager.getConnection()));

        dbManager.closeConnection();
    }

    public static void prepareDoctorSession(Doctor doctor, HttpSession session) {
        session.setAttribute("user", doctor);

        // Get the scheduled and available appointments from the database
        DatabaseManager dbManager = new DatabaseManager();
        session.setAttribute("appointmentsList", doctor.getScheduledAppointments(dbManager.getConnection()));

        dbManager.closeConnection();
    }

    public static void prepareAppointmentDeletionMessage(String message, HttpSession session) {
        session.setAttribute("AppointmentDeletionMessage", message);
    }

    public static void prepareDoctorRegistrationMessage(String message, HttpSession session) {
        session.setAttribute("registerDoctorMessage", message);
    }

    public static void prepareDoctorDeleteMessage(String message, HttpSession session) {
        session.setAttribute("deleteDoctorMessage", message);
    }
}

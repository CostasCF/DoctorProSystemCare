package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.models.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SessionManager {
    public static void prepareAdminSession(Admin admin, HttpSession session) {
        session.setAttribute("admin", admin);
    }

    public static void preparePatientSession(Patient patient, HttpSession session) {
        // Store the patient to a session
        session.setAttribute("patient", patient);

        // Get the scheduled and available appointments from the database
        DatabaseManager dbManager = new DatabaseManager();
        session.setAttribute("scheduledAppointments", patient.getScheduledAppointments(dbManager.getConnection()));
        session.setAttribute("oldAppointments", patient.getOldAppointments(dbManager.getConnection()));

        dbManager.closeConnection();
    }

    public static void prepareDoctorSession(Doctor doctor, HttpSession session) {
        session.setAttribute("doctor", doctor);

        // Get the scheduled and available appointments from the database
        DatabaseManager dbManager = new DatabaseManager();
        session.setAttribute("appointmentsList", doctor.getScheduledAppointments(dbManager.getConnection()));

        dbManager.closeConnection();
    }

    public static void prepareAppointmentDeletionMessage(String message, HttpSession session) {
        session.setAttribute("AppointmentDeletionMessage", message);
    }


}

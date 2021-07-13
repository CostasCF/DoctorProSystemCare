package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.models.AvailableAppointment;
import com.WebFlexers.models.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/schedule-appointment-servlet")
public class ScheduleAppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession();

        try {
            // Get the available appointment from appointmentID
            DatabaseManager dbManager = new DatabaseManager();
            String appointmentID = request.getParameter("scheduled_appointment_id");
            AvailableAppointment availableAppointment = AvailableAppointment.getFromDatabase(
                    Query.getAvailableAppointmentByID(dbManager.getConnection(), appointmentID));

            // Schedule the available appointment
            Patient patient = (Patient)session.getAttribute("patient");
            if (patient != null && availableAppointment != null)
                availableAppointment.scheduleThisAppointment(dbManager.getConnection(), patient.getAmka());

            dbManager.closeConnection();

        } catch (SQLException e) {
            System.out.println("An error occurred while scheduling appointment from available appointment");
        }

        getServletContext().getRequestDispatcher("/profile_patient.jsp").forward(request, response);
    }
}
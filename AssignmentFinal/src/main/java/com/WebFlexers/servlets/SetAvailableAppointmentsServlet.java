package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.models.AvailableAppointment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/set-available-appointments-servlet")
public class SetAvailableAppointmentsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession();

        // Get the specialty from the department dropdown menu
        String specialty = request.getParameter("selectDepartment");

        // Find all the available appointments of that specialty
        try {
            DatabaseManager dbManager = new DatabaseManager();

            ArrayList<AvailableAppointment> availableAppointments = AvailableAppointment.getMultipleFromDatabase(
                    Query.getAvailableAppointmentsByDoctorSpecialty(dbManager.getConnection(), specialty));
            session.setAttribute("specialtyAvailableAppointments", availableAppointments);

            dbManager.closeConnection();
        } catch (SQLException e) {
            System.out.println("An error occurred while setting available appointments in servlet");
        }

        getServletContext().getRequestDispatcher("/profile_patient.jsp").forward(request, response);
    }
}
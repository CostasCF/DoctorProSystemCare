package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Appointment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/available-appointments-servlet")
public class AppointmentsAvailableServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        String specialty = request.getParameter("selectDepartment");
        String patient_amka = (String) session.getAttribute("amka");
        System.out.println(patient_amka + " " + specialty);

        DatabaseManager databaseManager = new DatabaseManager();
        ArrayList<Appointment> available_apps;
        available_apps = databaseManager.getAvailableAppointmentsBySpecialty(specialty, patient_amka);
        databaseManager.closeConnection();

        session.setAttribute("listAvailableAppointments", available_apps);
        getServletContext().getRequestDispatcher("/profile_patient.jsp").forward(request, response);
    }
}

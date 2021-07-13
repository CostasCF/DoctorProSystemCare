package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.models.ScheduledAppointment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/appointment-delete-servlet")
public class AppointmentDeletionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String appointment_id = request.getParameter("appointment_id");
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.CancelScheduledAppointment(appointment_id);
        databaseManager.closeConnection();
        getServletContext().getRequestDispatcher("/profile_patient.jsp").forward(request, response);
    }
}

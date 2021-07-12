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
        DatabaseManager databaseManager = new DatabaseManager();
        ArrayList<Appointment> available_apps = new ArrayList<>();
       // available_apps = databaseManager.getAvailableAppointmentsByDoctor();
    }
}

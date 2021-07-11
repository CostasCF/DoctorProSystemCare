package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Patient;
import com.WebFlexers.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/appointment-delete-servlet")
public class AppointmentDeletionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String appointment_id = request.getParameter("appointment_id");
        String amka = request.getParameter("amka");

        DatabaseManager database = new DatabaseManager();
        Patient patient = database.getPatientByAmka(amka);
        //patient.cancelAppointment();
        database.CancelScheduledAppointment(appointment_id);

        response.setContentType("text/html;charset=UTF-8");

        getServletContext().getRequestDispatcher("/profile_patient.jsp").forward(request, response);
    }
}

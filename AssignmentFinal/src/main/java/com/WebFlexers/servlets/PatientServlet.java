package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/patient-servlet")
public class PatientServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //On every refresh the servlet fetch data from the database and provides them to the front-end
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        DatabaseManager databaseManager = new DatabaseManager();
        Patient patient = databaseManager.getPatientByAmka((String)session.getAttribute("amka"));

        session.setAttribute("amka", patient.getAmka());
        session.setAttribute("username", patient.getUsername());
        session.setAttribute("firstname", patient.getFirstName());
        session.setAttribute("surname", patient.getSurname());
        session.setAttribute("email", patient.getEmail());
        session.setAttribute("phoneNumber", patient.getPhoneNumber());
        session.setAttribute("listAppointments", databaseManager.getScheduledAppointmentsByPatient(patient));
        //session.setAttribute("availableAppointments", databaseManager.getScheduledAppointmentsByPatient(patient));

        databaseManager.closeConnection();
    }
}
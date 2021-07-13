package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.ScheduledAppointment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/appointment-delete-servlet")
public class AppointmentDeletionServlet extends HttpServlet {

    public static void listAppointments(HttpServletRequest request, String amka, DatabaseManager database){
        try{
            HttpSession session = request.getSession();
            ArrayList<ScheduledAppointment> appointments;
            appointments = database.getScheduledAppointmentsByPatient(database.getPatientByAmka(amka));
            session.setAttribute("listAppointments", appointments);
        }catch (Exception e){
            System.out.println("Problem with listing doctors on admin's page : " + e.getMessage());
        }
    }

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

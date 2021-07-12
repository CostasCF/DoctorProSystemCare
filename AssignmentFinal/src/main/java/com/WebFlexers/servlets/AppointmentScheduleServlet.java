package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Appointment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/schedule-appointment-servlet")
public class AppointmentScheduleServlet extends HttpServlet {

    public static void listAppointmentsHistory(HttpServletRequest request, String amka, DatabaseManager database){
        try{
            HttpSession session = request.getSession();
            ArrayList<Appointment> appointmentsHistory;
            appointmentsHistory = database.getAppointmentsHistoryByPatient(database.getPatientByAmka(amka));
            session.setAttribute("listAppointmentsHistory", appointmentsHistory);
        }catch (Exception e){
            System.out.println("Problem with listing appointments history on patient's page : " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        String appointment_id = request.getParameter("scheduled_appointment_id");
        String patient_amka = (String) session.getAttribute("amka");

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.ScheduleAppointment(appointment_id, patient_amka);

        getServletContext().getRequestDispatcher("/profile_patient.jsp").forward(request, response);
    }
}

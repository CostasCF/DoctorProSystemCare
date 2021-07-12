package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Admin;
import com.WebFlexers.models.Appointment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InsertAvailableAppointmentServlet", value = "/InsertAvailableAppointmentServlet")
public class InsertAvailableAppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DatabaseManager database = new DatabaseManager();

        String doctorAMKA = (String) session.getAttribute("amka");
        String appointmentID = DatabaseManager.generateRandomId();
        String dateInsert = request.getParameter("dateInsert");
        String startTimeInsert = request.getParameter("startTimeInsert");
        String endTimeInsert = request.getParameter("endTimeInsert");

        try{
            boolean isDone = database.addAvailableAppointment(new Appointment(appointmentID,dateInsert,startTimeInsert,endTimeInsert), doctorAMKA); //if register is successful, redirect to admin's profile page, else print out an error
            if (isDone)
                request.setAttribute("insertAvailabilityMsg","Success inserting available appointments.");

        }catch (Exception e){
            request.setAttribute("insertAvailabilityMsg",e.getMessage());

        }


        database.closeConnection();
        getServletContext().getRequestDispatcher("/profile_doctor.jsp").forward(request, response);

    }
}

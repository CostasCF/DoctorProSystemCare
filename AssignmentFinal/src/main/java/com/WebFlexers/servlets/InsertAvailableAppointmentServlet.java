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

    /**
     * Inserts an available appointment to the database
     * @param request : The html request
     * @param response : The html response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DatabaseManager database = new DatabaseManager();

        String doctorAMKA = (String)session.getAttribute("amka");
        String appointmentID = DatabaseManager.generateRandomId();
        String dateInsert = request.getParameter("dateInsert");
        String startTimeInsert = request.getParameter("startTimeInsert");
        String endTimeInsert = request.getParameter("endTimeInsert");

        try {
            database.addAvailableAppointment(new Appointment(appointmentID,dateInsert,startTimeInsert,endTimeInsert), doctorAMKA);
            request.setAttribute("insertAvailabilityMsg","Success inserting available appointments.");

        } catch (Exception e) {
            request.setAttribute("insertAvailabilityMsg",e.getMessage());
            System.out.println("An error occured while inserting available appointments to the database");
            System.out.println(e.getMessage());
        }

        database.closeConnection();
        getServletContext().getRequestDispatcher("/profile_doctor.jsp").forward(request, response);

    }
}

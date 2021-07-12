package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Appointment;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "doctor-servlet", value = "/doctor-servlet")
public class DeleteAppointmentServlet extends HttpServlet {

    /**
     * Deletes an appointment from the database
     * @param request : The html request
     * @param response : The html response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String apptID = request.getParameter("ApptID");
        DatabaseManager databaseManager = new DatabaseManager();
        Appointment appointment = databaseManager.getAppointmentById(apptID);

        // Get the date and start time of the appointment and combine them
        LocalDate appointmentDate = appointment.getDate();
        LocalTime appointmentStartTime = appointment.getStart_time();
        LocalDateTime appointmentDateTime = LocalDateTime.of(appointmentDate, appointmentStartTime);

        // Cancel the appointment if it is scheduled in less than 3 days from now
        if (Duration.between(appointmentDateTime, LocalDateTime.now()).toDays() > 3) {
            databaseManager.CancelScheduledAppointment(apptID);
        }
        else {
            request.setAttribute("cancelAppointmentError", "Can't delete appointment, because it is scheduled in less than 3 days from now");
            System.out.println("Can't delete appointment, because it is scheduled in less than 3 days from now");
        }

        databaseManager.closeConnection();
        getServletContext().getRequestDispatcher("/profile_doctor.jsp").forward(request, response);
    }
}

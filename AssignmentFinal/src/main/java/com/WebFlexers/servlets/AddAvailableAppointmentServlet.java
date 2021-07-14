package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.StringUtility;
import com.WebFlexers.models.*;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.DAYS;

@WebServlet("/add-available-appointment-servlet")
public class AddAvailableAppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the date and time from html form
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(request.getParameter("date"), dateFormatter);

        LocalTime startTime = LocalTime.parse(request.getParameter("startTime"));
        LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));

        // Get the doctor instance to get access to their amka
        HttpSession session = request.getSession();
        Doctor doctor = (Doctor)session.getAttribute("user");
        String appointmentID = StringUtility.generateRandomId();

        // Create an available appointment and add it to the database
        DatabaseManager dbManager = new DatabaseManager();

        AvailableAppointment appointment = new AvailableAppointment(appointmentID, doctor.getAmka(), date, startTime, endTime);

        // If the appointment object was correctly created add it
        if (date != null) {
            appointment.addToDatabase(dbManager.getConnection());
            System.out.println("Successfully added available appointment to database");

            // Refresh the page
            request.setAttribute("addAppointmentAvailabilityMessage","Successfully added available appointment to database");
            getServletContext().getRequestDispatcher("/profile_doctor.jsp").forward(request, response);
        }

    }
}
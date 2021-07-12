package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Appointment;
import  java.time.temporal.ChronoUnit;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@WebServlet(name = "doctor-servlet", value = "/doctor-servlet")
public class DoctorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String apptID = request.getParameter("ApptID");
        DatabaseManager databaseManager = new DatabaseManager();
        Appointment apt = databaseManager.getAppointmentById(apptID);


      //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
       // LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
  //      if (DAYS.between(, LocalDateTime.now()))

//https://www.java67.com/2016/04/how-to-convert-string-to-localdatetime-in-java8-example.html
        databaseManager.CancelScheduledAppointment(apptID);
        databaseManager.closeConnection();

    }
}

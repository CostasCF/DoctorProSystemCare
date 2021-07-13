package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.models.Doctor;
import com.WebFlexers.models.Patient;
import com.WebFlexers.models.ScheduledAppointment;
import com.WebFlexers.models.User;

import java.sql.SQLException;
import javax.print.Doc;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;


@WebServlet("/delete-scheduled-appointment-servlet")
public class DeleteScheduledAppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        DatabaseManager dbManager = new DatabaseManager();
        String deleteResultMessage = null; // A message that indicates if the appointment got deleted
        try {
            // Get the appointment id in order to delete the appointment
            String appointmentID = request.getParameter("appointment_id");

            ScheduledAppointment appointment = ScheduledAppointment.getFromDatabase(
                    Query.getScheduledAppointmentByID(dbManager.getConnection(), appointmentID));

            if (appointment != null) {
                // Get the date and time of the appointment and compare them with now
                LocalDateTime appointmentDateTime = LocalDateTime.of(appointment.getDate(), appointment.getStartTime());

                if (DAYS.between(LocalDateTime.now(), appointmentDateTime) > 3) {
                    appointment.removeFromDatabase(dbManager.getConnection());
                    deleteResultMessage = "Successfully deleted appointment with ID " + appointment.getAppointmentID();
                    System.out.println(deleteResultMessage);
                }
                else {
                    deleteResultMessage = "Couldn't delete appointment with ID " + appointment.getAppointmentID() + ", because " +
                            "it is scheduled for less than 3 days from now";
                    System.out.println(deleteResultMessage);
                }
                SessionManager.prepareAppointmentDeletionMessage(deleteResultMessage, session);
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while deleting a scheduled appointment");
            System.out.println(e.getMessage());
        }

        dbManager.closeConnection();

        // Determine if the user is a doctor or a patient and redirect to the correct page
        User user = (User)session.getAttribute("user");
        if (user instanceof Patient) {
            SessionManager.prepareAppointmentDeletionMessage(deleteResultMessage,session);
            // Prepare the patient session again to update data and refresh the page
            SessionManager.preparePatientSession((Patient)user, session);
            getServletContext().getRequestDispatcher("/profile_patient.jsp").forward(request, response);
        }
        else if (user instanceof Doctor) {
            SessionManager.prepareAppointmentDeletionMessage(deleteResultMessage,session);
            // Prepare the doctor session again to update data and refresh the page
            SessionManager.prepareDoctorSession((Doctor)user, session);
            getServletContext().getRequestDispatcher("/profile_doctor.jsp").forward(request, response);
        }

    }
}

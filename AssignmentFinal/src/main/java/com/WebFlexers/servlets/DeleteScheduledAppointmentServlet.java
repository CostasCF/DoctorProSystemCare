package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.models.Patient;
import com.WebFlexers.models.ScheduledAppointment;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet("/delete-scheduled-appointment-servlet")
public class DeleteScheduledAppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient)session.getAttribute("patient");

        DatabaseManager databaseManager = new DatabaseManager();
        try {
            // Get the appointment id in order to delete the appointment
            String appointmentID = request.getParameter("appointment_id");

            ScheduledAppointment appointment = ScheduledAppointment.getFromDatabase(
                    Query.getScheduledAppointmentByID(databaseManager.getConnection(), appointmentID));

            if (appointment != null)
                patient.cancelAppointment(databaseManager.getConnection(), appointment);

        } catch (SQLException e) {
            System.out.println("An error occurred while deleting a scheduled appointment");
            System.out.println(e.getMessage());
        }

        databaseManager.closeConnection();

        // Prepare the patient session again to update data and refresh the page
        SessionManager.preparePatientSession(patient, session);
        getServletContext().getRequestDispatcher("/profile_patient.jsp").forward(request, response);
    }
}

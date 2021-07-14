package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.models.Admin;
import com.WebFlexers.models.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-doctor-servlet")
public class DeleteDoctorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String amka = request.getParameter("amka");

        // Get the doctor from their amka and remove them from the database
        String deleteDoctorMessage = null;
        try {
            DatabaseManager dbManager = new DatabaseManager();
            Doctor doctor = Doctor.getFromDatabase(Query.getDoctorByAmka(dbManager.getConnection(), amka));

            if (doctor != null) {
                doctor.removeFromDatabase(dbManager.getConnection());
                deleteDoctorMessage = "Successfully deleted doctor";
            }
            else {
                deleteDoctorMessage = "Failed to delete doctor";
            }

            dbManager.closeConnection();
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting doctor");
            System.out.println(e.getMessage());
        }

        // Update the admin and redirect
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("user");

        SessionManager.prepareDoctorDeleteMessage(deleteDoctorMessage, request.getSession());
        SessionManager.prepareAdminSession(admin, session);
        getServletContext().getRequestDispatcher("/profile_admin.jsp").forward(request, response);
    }
}
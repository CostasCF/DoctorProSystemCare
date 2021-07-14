package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.PasswordAuthentication;
import com.WebFlexers.models.Admin;
import com.WebFlexers.models.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-doctor-servlet")
public class AddDoctorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get all doctor data and hash the password
        PasswordAuthentication crypto = new PasswordAuthentication();

        String amka = request.getParameter("amkaD");
        String username = request.getParameter("usernameD");
        String password = crypto.hash(request.getParameter("passwordD").toCharArray());
        String firstName = request.getParameter("firstNameD");
        String surname = request.getParameter("lastNameD");
        String specialty = request.getParameter("specialtyD");
        String email = request.getParameter("emailD");
        String phoneNum = request.getParameter("phoneNumD");

        // Get an instance of the admin to retrieve the adminID
        Admin admin = (Admin)request.getSession().getAttribute("user");
        String adminID = admin.getAdminID();

        // Create a new doctor and add him to the database
        DatabaseManager dbManager = new DatabaseManager();
        Doctor doctor = new Doctor(amka, username, password, firstName, surname, specialty, phoneNum, email, adminID);
        doctor.addToDatabase(dbManager.getConnection());

        // Prepare session and refresh
        SessionManager.prepareDoctorRegistrationMessage("Doctor successfully registered", request.getSession());
        SessionManager.prepareAdminSession(admin, request.getSession());
        getServletContext().getRequestDispatcher("/profile_admin.jsp").forward(request, response);
    }
}

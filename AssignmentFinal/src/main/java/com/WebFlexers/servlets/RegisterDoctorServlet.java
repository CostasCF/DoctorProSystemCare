package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.models.Doctor;
import com.WebFlexers.models.Patient;
import com.WebFlexers.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register-doctor-servlet")
public class RegisterDoctorServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String amkaD = request.getParameter("amkaD");
        String specialtyD = request.getParameter("specialtyD");
        String usernameD = request.getParameter("usernameD");
        String passwordD = request.getParameter("passwordD");
        String emailD = request.getParameter("emailD");
        String firstNameD = request.getParameter("firstNameD");
        String lastNameD = request.getParameter("lastNameD");
        String phoneNumD = request.getParameter("phoneNumD");
        String adminID = (String) session.getAttribute("adminID");

        DatabaseManager database = new DatabaseManager();

        // Check if the username already exists and if a patient or a doctor with the same amka already exists
        User user = User.getFromDatabase(database.getConnection(), usernameD);

        try {
            if (user == null) {
                // If no patient or doctor with the same amka exists in the database create a patient and add him to the database
                if (Patient.getFromDatabase(Query.getPatientByAmka(database.getConnection(), amkaD)) == null ||
                        Doctor.getFromDatabase(Query.getDoctorByAmka(database.getConnection(), amkaD)) == null) {

                    Doctor doctor = new Doctor(amkaD, usernameD, passwordD, firstNameD, lastNameD, specialtyD, phoneNumD,
                            emailD, adminID);
                    doctor.addToDatabase(database.getConnection());

                    // Get the session and login the newly created patient
                    SessionManager.prepareDoctorSession(doctor,session);
                    LoginServlet.setLoggedIn(true);
                    LoginServlet.setWhoLoggedIn("doctor");
                    getServletContext().getRequestDispatcher("/profile_doctor.jsp").forward(request, response);
                }
                else {
                    request.setAttribute("registerDoctorError","A user with the same amka already exists");
                    System.out.println("A user with the same amka already exists");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request,response);
                }
            }
            else {
                request.setAttribute("registerDoctorError","This username already exists");
                System.out.println("This username already exists");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request,response);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while registering patient");
            System.out.println(e.getMessage());
        }

        AdminServlet.listDoctors(request,database);
        database.closeConnection();
        if(session.getAttribute("IsSuperUser").equals("true"))
            getServletContext().getRequestDispatcher("/profile_admin_superuser.jsp").forward(request, response);
        else
            getServletContext().getRequestDispatcher("/profile_admin.jsp").forward(request, response);

    }
}

package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.PasswordAuthentication;
import com.WebFlexers.Query;
import com.WebFlexers.models.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register-patient-servlet")
public class RegisterPatientServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PasswordAuthentication crypto = new PasswordAuthentication();

        String amka = request.getParameter("amka");
        String username = request.getParameter("username");
        String password = crypto.hash((request.getParameter("password")).toCharArray());
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNum = request.getParameter("phoneNum");

        DatabaseManager database = new DatabaseManager();

        // Check if the username already exists and if a patient or a doctor with the same amka already exists
        User user = User.getFromDatabase(database.getConnection(), username);

        try {
            if (user == null) {
                // If no patient or doctor with the same amka exists in the database create a patient and add him to the database
                if (Patient.getFromDatabase(Query.getPatientByAmka(database.getConnection(), amka)) == null ||
                    Doctor.getFromDatabase(Query.getDoctorByAmka(database.getConnection(), amka)) == null) {

                    Patient patient = new Patient(amka, username, password, firstName, lastName, email, phoneNum);
                    patient.addToDatabase(database.getConnection());

                    // Get the session and login the newly created patient
                    HttpSession session = request.getSession();
                    SessionManager.preparePatientSession(patient,session);
                    LoginServlet.setLoggedIn(true);
                    LoginServlet.setWhoLoggedIn("patient");
                    getServletContext().getRequestDispatcher("/profile_patient.jsp").forward(request, response);
                }
                else {
                    request.setAttribute("registerError","A user with the same amka already exists");
                    System.out.println("A user with the same amka already exists");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request,response);
                }
            }
            else {
                request.setAttribute("registerError","A user with the same amka already exists");
                System.out.println("A user with the same username already exists");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request,response);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while registering patient");
            System.out.println(e.getMessage());
        }

        database.closeConnection();

    }
}
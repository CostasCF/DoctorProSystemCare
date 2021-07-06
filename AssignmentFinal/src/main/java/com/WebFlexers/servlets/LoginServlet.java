package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Admin;
import com.WebFlexers.models.Doctor;
import com.WebFlexers.models.Patient;
import com.WebFlexers.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {

    /**
     * Checks if a user with the given username and password exists in the database
     * @param username : The user's username
     * @param password : The user's password
     * @return True if a user with the given credentials matches one in the database and false otherwise
     */
    protected static User userLogin(String username, String password) {
        DatabaseManager database = new DatabaseManager();

        if (database.getConnection() != null) {
            // Check if a patient with the given credentials exists
            Patient patient = database.getPatient(username, password);

            if (patient != null) {
                database.closeConnection();
                return patient;
            }

            // Check if a doctor with the given credentials exists
            Doctor doctor = database.getDoctor(username, password);

            if (doctor != null) {
                database.closeConnection();
                return doctor;
            }

            // Check if an admin with the given credentials exists
            Admin admin = database.getAdmin(username, password);

            if (admin != null) {
                database.closeConnection();
                return admin;
            }

            // If the user is not found return null
            return null;
        }
        else {
            return null;
        }
    }

    protected void preparePatientSession(Patient patient, HttpSession session) {
        session.setAttribute("amka", patient.getAmka());
        session.setAttribute("username", patient.getUsername());
        session.setAttribute("firstname", patient.getName());
        session.setAttribute("surname", patient.getSurname());
        session.setAttribute("email", patient.getEmail());
        session.setAttribute("phoneNumber", patient.getPhoneNumber());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address;

        //User validation
        User user = userLogin(username, password);

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        if (user instanceof Patient)
        {
            Patient patient = (Patient)user;
            preparePatientSession(patient, session);

            //ArrayList<Appointment> appointmentDetails = Patient.getAppointmentsHistory(patient.getAmka());
            //session.setAttribute("appointment_list", appointmentDetails); //appointment_column

            address= "/profile.jsp";
        }
        else if (user instanceof Doctor) {
            System.out.println("The guy is a doctor");
            address = "/profile.jsp";
        }
        else if (user instanceof Admin) {
            System.out.println("The guy is an admin");
            address = "/profile.jsp";
        }
        else
        {
            System.out.println("Error, Invalid username or password");
            request.setAttribute("error","Invalid Username or Password");
            address= "/index.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }
}

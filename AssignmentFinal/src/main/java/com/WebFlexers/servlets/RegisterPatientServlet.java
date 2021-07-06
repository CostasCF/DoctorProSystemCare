package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Admin;
import com.WebFlexers.models.Doctor;
import com.WebFlexers.models.Patient;
import com.WebFlexers.models.User;

import javax.crypto.spec.PSource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register-patient-servlet")
public class RegisterPatientServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String amka = request.getParameter("amka");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNum = request.getParameter("phoneNum");

        DatabaseManager database = new DatabaseManager();

        // Check if the username already exists and if a patient or a doctor with the same amka already exists
        if (database.getUserByUsername(username) == null) {
            if (database.getPatientByAmka(amka) == null) {
                if (database.getDoctorByAmka(amka) == null)
                    database.registerPatient(new Patient(amka, username, password, firstName, lastName, email, phoneNum));
                else {
                    System.out.println("A doctor with the same amka already exists");
                }
            }
            else {
                System.out.println("A patient with the same amka already exists");
            }
        }
        else {
            System.out.println("This username already exists");
        }

        database.closeConnection();

    }
}
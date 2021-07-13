package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.*;

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
                if (database.getDoctorByAmka(amka) == null){
                    HttpSession session = request.getSession();
                    Patient patient = new Patient(amka, username, password, firstName, lastName, email, phoneNum);
                    boolean isDone = database.registerPatient(patient); //if register is successful, redirect to admin's profile page, else print out an error
                    if(isDone)
                    {
                        LoginServlet loginServlet = new LoginServlet();
                        SessionManager.preparePatientSession(patient,session);
                        LoginServlet.setLoggedIn(true);
                        LoginServlet.setWhoLoggedIn("patient");
                        getServletContext().getRequestDispatcher("/profile_patient.jsp").forward(request, response);
                    }
                }
                else {
                    request.setAttribute("registerError","A doctor with the same amka already exists");
                    System.out.println("A doctor with the same amka already exists");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request,response);
                }
            }
            else {
                request.setAttribute("registerError","A patient with the same amka already exists");
                System.out.println("A patient with the same amka already exists");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request,response);
            }
        }
        else {
            request.setAttribute("registerError","This username already exists");
            System.out.println("This username already exists");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
        }

        database.closeConnection();

    }
}
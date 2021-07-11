package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Doctor;
import com.WebFlexers.models.Patient;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;

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
        System.out.println("Admin id posting in RegisterDoctorServlet: " +adminID);
        DatabaseManager database = new DatabaseManager();

        // Check if the username already exists and if a patient or a doctor with the same amka already exists
        if (database.getUserByUsername(usernameD) == null) {
            if (database.getPatientByAmka(amkaD) == null) {
                if (database.getDoctorByAmka(amkaD) == null)
                    database.registerDoctor(new Doctor(amkaD, usernameD, passwordD, firstNameD, lastNameD, specialtyD, phoneNumD, emailD, adminID));
                else {
                    request.setAttribute("registerDoctorError","A doctor with the same amka already exists");
                    System.out.println("A doctor with the same amka already exists");
                }
            }
            else {
                request.setAttribute("registerDoctorError","A patient with the same amka already exists");
                System.out.println("A patient with the same amka already exists");
            }
        }
        else {
            request.setAttribute("registerDoctorError","This username already exists");
            System.out.println("This username already exists");
        }

        AdminServlet.listDoctors(request,database);
        database.closeConnection();
        if(session.getAttribute("IsSuperUser").equals("true"))
            getServletContext().getRequestDispatcher("/profile_admin_superuser.jsp").forward(request, response);
        else
            getServletContext().getRequestDispatcher("/profile_admin.jsp").forward(request, response);


    }
}

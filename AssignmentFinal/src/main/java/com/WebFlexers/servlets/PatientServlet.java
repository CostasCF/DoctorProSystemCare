package com.WebFlexers.servlets;

import com.WebFlexers.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

// Currently in progress, not working
@WebServlet("/patient-servlet")
public class PatientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean patientIsValid = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address;
        System.out.println(username);
        System.out.println(password);

        Patient _patient;

        //User validation
        User user = User.login(username, password);

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        if (user instanceof Patient)
        {
            Patient patient = (Patient)user;
            session.setAttribute("amka",(patient.getAmka()));
            session.setAttribute("username",patient.getUsername());
            session.setAttribute("firstname",patient.getName());
            session.setAttribute("surname",patient.getSurname());
            session.setAttribute("email",patient.getEmail());
            session.setAttribute("phoneNumber",patient.getPhoneNumber());

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

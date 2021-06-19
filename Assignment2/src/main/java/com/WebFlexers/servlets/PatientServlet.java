package com.WebFlexers.servlets;

import com.WebFlexers.models.Patient;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
        try {
            patientIsValid = Patient.validatePatient(username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        if(patientIsValid)
        {
            String patientDetails[] = Patient.getPatientDetails(username);
            session.setAttribute("amka",patientDetails[0]);
            session.setAttribute("username",patientDetails[1]);
            session.setAttribute("firstname",patientDetails[3]);
            session.setAttribute("surname",patientDetails[4]);
            session.setAttribute("email",patientDetails[5]);
            session.setAttribute("phoneNumber",patientDetails[6]);

            ArrayList<String[]> appointmentDetails = Patient.getAppointmentsHistory(patientDetails[0]);
            session.setAttribute("appointment_list", appointmentDetails); //appointment_column

            address= "/profile.jsp";
        }
        else
        {
            System.out.println("Error");
            request.setAttribute("error","Invalid Username or Password");
            address= "/index.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }
}

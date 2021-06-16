package com.WebFlexers.servlets;

import com.WebFlexers.models.Patient;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/loginPage")
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
            _patient = Patient.viewPatientDetails(""); //dinoume to amka gia na psaksei kai ta upoloipa stoixeia
            session.setAttribute("username",username);
            session.setAttribute("amka",_patient.amka);
            session.setAttribute("phoneNumber",_patient.phoneNumber);
            session.setAttribute("firstname",_patient.firstname);
            session.setAttribute("surname",_patient.surname);
            session.setAttribute("email",_patient.email);


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

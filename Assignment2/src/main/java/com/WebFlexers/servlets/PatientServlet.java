package com.WebFlexers.servlets;

import com.WebFlexers.models.Patient;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import com.WebFlexers.models.Patient;

@WebServlet("/loginPage")
public class PatientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Patient patient = new Patient("", "","","","");
        boolean isValid = false;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = "";

        //User validation
        try {
            isValid = patient.ValidatePatient(username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        //authentication with example credentials

        if(isValid)
        {
            session.setAttribute("username",username);
            address= "/home.jsp";
        }else
        {
            request.setAttribute("error","Invalid Username or Password");
            address= "/index.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }
}

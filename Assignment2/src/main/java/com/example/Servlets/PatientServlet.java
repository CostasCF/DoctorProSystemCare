package com.example.Servlets;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


public class PatientServlet extends HttpServlet
{
    public PatientServlet(){

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = "";

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        //authentication with example credentials
        //later we need to take the creds from the db

        if(username.equals("kostas") && password.equals("test"))
        {
            session.setAttribute("username",username);
            address= "/home.jsp";
        }else
            address = "/error.jsp";


       RequestDispatcher dispatcher = request.getRequestDispatcher(address);
       dispatcher.forward(request,response);


    }
}

package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/appointment-delete-servlet")
public class AppointmentDeletionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String appointment_id = request.getParameter("appointment_id");

        //User validation
        //DatabaseManager database = new DatabaseManager();
        //User user = database;

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

    }
}

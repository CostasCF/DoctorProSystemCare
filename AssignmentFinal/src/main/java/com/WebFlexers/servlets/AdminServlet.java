package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Doctor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-servlet")
public class AdminServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String AMKA = request.getParameter("AMKA");
            DatabaseManager database = new DatabaseManager();
            Doctor doc  =  database.getDoctorByAmka(AMKA);
            database.deleteDoctor(doc);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profileAdmin.jsp");
            dispatcher.forward(request,response);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Deletion problem: Doctor's amka doesn't exist");
        }

    }
}

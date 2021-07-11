package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Admin;
import com.WebFlexers.models.Doctor;
import com.WebFlexers.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin-servlet")
public class AdminServlet extends HttpServlet {
    String address;

    /**
     * Lists all doctors that exist from the database
     * @param request ttpServletRequest request
     * @param database DatabaseManager database
     */
    public static void listDoctors(HttpServletRequest request, DatabaseManager database){
        try{
            ArrayList<Doctor> doctors;
            doctors = database.getDoctors();
            request.setAttribute("listDoctors", doctors);
        }catch (Exception e){
            System.out.println("Problem with listing doctors on admin's page : " + e.getMessage());
        }

    }

    /**
     * Lists all admins that exist from the database
     * @param request ttpServletRequest request
     * @param database DatabaseManager database
     */
    public static void listAdmins(HttpServletRequest request, DatabaseManager database){
        try{
            ArrayList<Admin> admins;
            admins = database.getAdmins();
            request.setAttribute("listAdmins", admins);
        }catch (Exception e){
            System.out.println("Problem with listing doctors on admin's page : " + e.getMessage());
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
           String AMKA = request.getParameter("AMKA");
          DatabaseManager database = new DatabaseManager();
          Doctor doc  =  database.getDoctorByAmka(AMKA); //find the doctor to be deleted via it's amka
          Admin.DeleteDoctor(doc);
          getServletContext().getRequestDispatcher("/profile_admin.jsp").forward(request, response);

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Deletion problem: Doctor's amka doesn't exist");
        }
    }

}

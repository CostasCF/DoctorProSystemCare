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
        ArrayList<Doctor> doctors;

        doctors = database.getDoctors();
//		for (var doctor: doctors) {
//			System.out.println(doctor.getUsername());
//		}
        System.out.println("Successfully listed doctors.");
        request.setAttribute("listDoctors", doctors);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
           String AMKA = request.getParameter("AMKA");
          DatabaseManager database = new DatabaseManager();
          Doctor doc  =  database.getDoctorByAmka(AMKA);
          Admin.DeleteDoctor(doc);
          listDoctors(request,database);
            getServletContext().getRequestDispatcher("/profileAdmin.jsp").forward(request, response);

      //      address = "/profileAdmin.jsp";
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Deletion problem: Doctor's amka doesn't exist");
        }


    }
}

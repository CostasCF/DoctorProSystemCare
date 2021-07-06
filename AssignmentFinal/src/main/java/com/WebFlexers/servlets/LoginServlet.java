package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Admin;
import com.WebFlexers.models.Doctor;
import com.WebFlexers.models.Patient;
import com.WebFlexers.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {

    protected void prepareAdminSession(Admin admin, HttpSession session) {
        session.setAttribute("username", admin.getUsername());
        session.setAttribute("firstname", admin.getFirstName());
        session.setAttribute("surname", admin.getSurname());
        session.setAttribute("email", admin.getEmail());
    }
    protected void preparePatientSession(Patient patient, HttpSession session) {
        session.setAttribute("amka", patient.getAmka());
        session.setAttribute("username", patient.getUsername());
        session.setAttribute("firstname", patient.getFirstName());
        session.setAttribute("surname", patient.getSurname());
        session.setAttribute("email", patient.getEmail());
        session.setAttribute("phoneNumber", patient.getPhoneNumber());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address;

        //User validation
        DatabaseManager database = new DatabaseManager();
        User user = database.validateUser(username, password);
        database.closeConnection();

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        if (user instanceof Patient)
        {
            Patient patient = (Patient)user;
            preparePatientSession(patient, session);

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
            Admin admin = (Admin) user;
            prepareAdminSession(admin,session);
            admin.doctors = new ArrayList<>();

            DatabaseManager db = new DatabaseManager();
            admin.doctors = db.getDoctors();
            for (var doctor: admin.doctors) {
                System.out.println(doctor);
            }
            address = "/profileAdmin.jsp";
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

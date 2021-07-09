package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Admin;
import com.WebFlexers.models.Doctor;
import com.WebFlexers.models.Patient;
import com.WebFlexers.models.User;

import javax.print.Doc;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {

    private static Boolean isLoggedIn;
    private static String whoLoggedIn;

    public static String getWhoLoggedIn() {
        return whoLoggedIn;
    }

    public static void setWhoLoggedIn(String whoLoggedIn) {
        LoginServlet.whoLoggedIn = whoLoggedIn;
    }

    public void prepareAdminSession(Admin admin, HttpSession session) {
        session.setAttribute("username", admin.getUsername());
        session.setAttribute("firstname", admin.getFirstName());
        session.setAttribute("surname", admin.getSurname());
        session.setAttribute("email", admin.getEmail());
        session.setAttribute( "adminID", admin.getAdminID());
    }
    public void preparePatientSession(Patient patient, HttpSession session) {
        session.setAttribute("amka", patient.getAmka());
        session.setAttribute("username", patient.getUsername());
        session.setAttribute("firstname", patient.getFirstName());
        session.setAttribute("surname", patient.getSurname());
        session.setAttribute("email", patient.getEmail());
        session.setAttribute("phoneNumber", patient.getPhoneNumber());
    }
    public void prepareDoctorSession(Doctor doctor, HttpSession session) {
        session.setAttribute("amka", doctor.getAmka());
        session.setAttribute("username", doctor.getUsername());
        session.setAttribute("firstname", doctor.getFirstName());
        session.setAttribute("surname", doctor.getSurname());
        session.setAttribute("email", doctor.getEmail());
        session.setAttribute("phoneNumber", doctor.getPhoneNum());
        session.setAttribute("specialty", doctor.getSpecialty());
    }


    String address;

    public static Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public static void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //User validation
        DatabaseManager database = new DatabaseManager();
        User user = database.validateUser(username, password);

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        if (user instanceof Patient)
        {
            Patient patient = (Patient)user;
            preparePatientSession(patient, session);
            setLoggedIn(true);
            //ArrayList<Appointment> appointmentDetails = Patient.getAppointmentsHistory(patient.getAmka());
            //session.setAttribute("appointment_list", appointmentDetails); //appointment_column
            setWhoLoggedIn("patient");
            address= "/profile_patient.jsp";
        }
        else if (user instanceof Doctor) {
            System.out.println("The guy is a doctor");
            Doctor doctor = (Doctor)user;
            prepareDoctorSession(doctor,session);
            setLoggedIn(true);
            setWhoLoggedIn("doctor");
            address = "/profile_doctor.jsp";
        }
        else if (user instanceof Admin) {
            System.out.println("The guy is an admin");
            Admin admin = (Admin) user;
            prepareAdminSession(admin,session); //preparing admin's session
            address = "/profile_admin.jsp"; // forwarding to admin's profile page
            setLoggedIn(true);
            setWhoLoggedIn("admin");
        }
        else
        {
            System.out.println("Error, Invalid username or password");
            request.setAttribute("error","Invalid Username or Password");
            address = "/index.jsp";
        }

        database.closeConnection();
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }


}

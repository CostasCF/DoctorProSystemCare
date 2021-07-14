package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {

    // Attributes
    private static Boolean isLoggedIn;
    private static String whoLoggedIn;
    private String address;

    // Getters and Setters
    public static String getWhoLoggedIn() { return whoLoggedIn; }

    public static void setWhoLoggedIn(String whoLoggedIn) {
        LoginServlet.whoLoggedIn = whoLoggedIn;
    }

    public static Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public static void setLoggedIn(Boolean loggedIn) { isLoggedIn = loggedIn; }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Get the user with the given credentials
        DatabaseManager database = new DatabaseManager();
        User user = User.login(username, password);

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        if (user instanceof Patient)
        {
            System.out.println("The user is a patient");
            Patient patient = (Patient)user;
            SessionManager.preparePatientSession(patient, session);
            isLoggedIn = true;
            whoLoggedIn = "patient";
            address= "/profile_patient.jsp";
        }
        else if (user instanceof Doctor) {
            System.out.println("The user is a doctor");
            Doctor doctor = (Doctor)user;
            SessionManager.prepareDoctorSession(doctor,session);
            // Doctor.viewScheduledAppointments(request,doctor.getAmka(),database);
            setLoggedIn(true);
            setWhoLoggedIn("doctor");
            address = "/profile_doctor.jsp";
        }
        else if (user instanceof Admin) {
            System.out.println("The user is an admin");
            Admin admin = (Admin) user;
            SessionManager.prepareAdminSession(admin,session); // preparing admin's session

            if(admin.IsSuperUser())
                address = "/profile_admin_superuser.jsp"; // forwarding to admin's superuser profile page
            else
                address = "/profile_admin.jsp";
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

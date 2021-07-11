package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

import static com.WebFlexers.servlets.AdminServlet.listDoctors;

@WebServlet("/register-admin-servlet")
public class RegisterAdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean IsSuperUser;
        String checkbox = request.getParameter("isSuperUserA");
        String username = request.getParameter("usernameA");
        String password = request.getParameter("passwordA");
        String email = request.getParameter("emailA");
        String firstName = request.getParameter("firstNameA");
        String lastName = request.getParameter("lastNameA");
        if(checkbox != null)
            IsSuperUser = true;
        else
            IsSuperUser = false;
        System.out.println("Request get parameter ISsuperUser?" + IsSuperUser);
        System.out.println(IsSuperUser);

        DatabaseManager database = new DatabaseManager();
        String adminID = database.generateRandomId();

        if (database.getUserByUsername(username) == null) {
            Admin admin = new Admin(username, password, firstName, lastName, email,adminID,IsSuperUser);
            boolean isDone = database.registerAdmin(admin); //if register is successful, redirect to admin's profile page, else print out an error
            if (isDone)
            {
                AdminServlet.listAdmins(request,database);
                getServletContext().getRequestDispatcher("/profile_admin_superuser.jsp").forward(request, response);
            } else {
                request.setAttribute("registerError","Email already exists.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_admin_superuser.jsp");
                dispatcher.forward(request,response);
            }

        }
        else {
            System.out.println("User already exists");
            request.setAttribute("registerError","User already exists.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_admin_superuser.jsp");
            dispatcher.forward(request,response);
        }

        database.closeConnection();

    }
}

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        DatabaseManager database = new DatabaseManager();
        String adminID = database.generateRandomId();

        if (database.getUserByUsername(username) == null) {
            HttpSession session = request.getSession();
            Admin admin = new Admin(username, password, firstName, lastName, email,adminID);
            boolean isDone = database.registerAdmin(admin); //if register is successful, redirect to admin's profile page, else print out an error
            if (isDone)
            {
                LoginServlet loginServlet = new LoginServlet();
                loginServlet.prepareAdminSession(admin,session);
                LoginServlet.setLoggedIn(true);
                LoginServlet.setWhoLoggedIn("admin");
                getServletContext().getRequestDispatcher("/profile_admin.jsp").forward(request, response);
            } else {
                request.setAttribute("registerError","Email already exists.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request,response);
            }

        }
        else {
            System.out.println("User already exists");
            request.setAttribute("registerError","User already exists.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
        }

        database.closeConnection();

    }
}

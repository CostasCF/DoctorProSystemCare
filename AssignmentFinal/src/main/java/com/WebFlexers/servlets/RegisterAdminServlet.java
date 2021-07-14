package com.WebFlexers.servlets;

import com.WebFlexers.DatabaseManager;
import com.WebFlexers.Query;
import com.WebFlexers.StringUtility;
import com.WebFlexers.models.Admin;
import com.WebFlexers.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

@WebServlet("/register-admin-servlet")
public class RegisterAdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the attributes from the register admin panel
        boolean IsSuperUser;
        String checkbox = request.getParameter("isSuperUserA");
        String username = request.getParameter("usernameA");
        String password = request.getParameter("passwordA");
        String email = request.getParameter("emailA");
        String firstName = request.getParameter("firstNameA");
        String lastName = request.getParameter("lastNameA");

        // Check if the admin is super user
        IsSuperUser = checkbox != null;

        DatabaseManager database = new DatabaseManager();
        String adminID = StringUtility.generateRandomId();

        HttpSession session = request.getSession();

        try {
            // Check if a user with the same username already exists
            if (User.getFromDatabase(database.getConnection(), username) == null) {
                // Check if an admin with the same email already exists
                if (Admin.getFromDatabase(Query.getAdminByEmail(database.getConnection(), email)) == null) {
                    Admin admin = new Admin(username, password, firstName, lastName, email, adminID, IsSuperUser);
                    admin.addToDatabase(database.getConnection());
                    session.setAttribute("registerMessage","Successfully registered admin!");
                    getServletContext().getRequestDispatcher("/profile_admin.jsp").forward(request, response);
                }
                else {
                    session.setAttribute("registerMessage","Email already exists.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_admin_superuser.jsp");
                    dispatcher.forward(request,response);
                }
            }
            else {
                System.out.println("Username already exists");
                session.setAttribute("registerMessage","Username already exists.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_admin_superuser.jsp");
                dispatcher.forward(request,response);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while registering an admin to the database");
            System.out.println(e.getMessage());
        }

        database.closeConnection();
    }
}

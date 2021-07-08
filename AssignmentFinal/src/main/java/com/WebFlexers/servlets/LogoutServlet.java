package com.WebFlexers.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


/**
 * Logs out the user by deleting the session
 */
@WebServlet("/logout-servlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get the session and delete it
        HttpSession session = request.getSession();
        session.invalidate();
        LoginServlet.setLoggedIn(false);
        // Redirect to the home page
        var address = "index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }
}

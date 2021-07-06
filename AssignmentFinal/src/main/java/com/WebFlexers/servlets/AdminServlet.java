package com.WebFlexers.servlets;


import com.WebFlexers.DatabaseManager;
import com.WebFlexers.models.Doctor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class AdminServlet extends HttpServlet {
    //private DatabaseManager databaseManager;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                  //  showNewForm(request, response);
                    break;
                case "/insert":
                 //   insertBook(request, response);
                    break;
                case "/delete":
                  //  deleteBook(request, response);
                    break;
                case "/edit":
                 //   showEditForm(request, response);
                    break;
                case "/update":
                   // updateBook(request, response);
                    break;
                default:
                    listDoctors(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listDoctors(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
       ArrayList<Doctor> doctors;

        DatabaseManager db = new DatabaseManager();
        doctors = db.getDoctors();
        for (var doctor: doctors) {
            System.out.println(doctor.getUsername());
        }
        request.setAttribute("listDoctors",   doctors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("profileAdmin.jsp");
        dispatcher.forward(request, response);
    }

//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        String id = request.getParameter("id");
//        Doctor existingDoctor = databaseManager.getDoctorByAmka(id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
//        request.setAttribute("book", existingDoctor);
//        dispatcher.forward(request, response);
//
//    }
//
//    private void insertBook(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        String title = request.getParameter("title");
//        String author = request.getParameter("author");
//        float price = Float.parseFloat(request.getParameter("price"));
//
//        Book newBook = new Book(title, author, price);
//        databaseManager.insertBook(newBook);
//        response.sendRedirect("list");
//    }
//
//    private void updateBook(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String title = request.getParameter("title");
//        String author = request.getParameter("author");
//        float price = Float.parseFloat(request.getParameter("price"));
//
//        Book book = new Book(id, title, author, price);
//        databaseManager.updateBook(book);
//        response.sendRedirect("list");
//    }
//
//    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        Book book = new Book(id);
//        databaseManager.deleteBook(book);
//        response.sendRedirect("list");
//
//    }
}
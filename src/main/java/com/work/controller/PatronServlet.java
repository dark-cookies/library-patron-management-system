package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.work.bo.PatronBO;
import com.work.model.Book;
import com.work.model.Student;

/**
 * Servlet implementation class PatronServlet
 */
public class PatronServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatronServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        int rollNo = Integer.parseInt(request.getParameter("rollNo"));
        String nextViewPage = "error.jsp";
        PatronBO Bo = new PatronBO();
        Student stud = Bo.getStudentInfo(rollNo);
        // If student is found
        if (stud != null) {
            request.setAttribute("user", stud);
            ArrayList<Book> List = Bo.patronList(rollNo);

            Iterator<Book> it = List.iterator();
            Book bookObj = null;
            while (it.hasNext()) {
                bookObj = it.next();
                System.out.println(bookObj.getBookName());
            }

            request.setAttribute("checklist", List);
            nextViewPage = "/patrondetails.jsp";
        }
        // If student is not found
        else {
            nextViewPage = "/error.jsp?q=sp";
        }
        request.getRequestDispatcher(nextViewPage).forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

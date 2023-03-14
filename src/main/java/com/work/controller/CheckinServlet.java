package com.work.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.work.bo.*;
import com.work.exception.SGCADBException;
import com.work.model.Student;
import com.work.model.Book;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Servlet implementation class CheckinServlet
 */
public class CheckinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // checking the form action
        String data = request.getParameter("data");

        String nextViewPage = "error.jsp";

        switch (data) {
        // checking for previous checkout entry
        case "checkStudent":
            boolean status = checkStudent(request, response);
            if (status) {
                nextViewPage = "/checkinbook.jsp";
            } else {
                nextViewPage = "/error.jsp?q=f";
            }
            break;

        // check in of book
        case "checkin":
            status = checkIn(request, response);
            if (status) {
                nextViewPage = "/success.jsp?q=Checked in Successfully";
            } else {
                nextViewPage = "/error.jsp?q=c";
            }
            break;
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

    /**
     * This method checks if the user exists and retrieves the due list
     * 
     * @param request  An object which is given by tomcat and contains all the
     *                 data passed from client side
     * @param response An object which is given by tomcat to return the data
     * @return boolean This returns a boolean indicating the status of the
     *         method
     * @throws SGCADBException
     */
    public boolean checkStudent(HttpServletRequest request,
            HttpServletResponse response) {
        int rollNo = Integer.parseInt(request.getParameter("rollNo"));
        boolean status = false;
        request.getSession().setAttribute("roll", rollNo);
        CheckInBO Bo = new CheckInBO();
        ArrayList<Book> List = Bo.getList(rollNo);
        // If the list is empty
        if (List.isEmpty()) {
            status = false;
        }
        // If the list is not empty
        else {
            Iterator<Book> it = List.iterator();
            Book bookObj = null;
            while (it.hasNext()) {
                bookObj = it.next();
                System.out.println(bookObj.getBookName());
            }
            status = true;
            request.setAttribute("checklist", List);
        }
        return status;
    }

    /**
     * This method inserts records into the database when user checks in
     * 
     * @param request  An object which is given by tomcat and contains all the
     *                 data passed from client side
     * @param response An object which is given by tomcat to return the data
     * @return boolean This returns a boolean indicating the status of the
     *         method
     * @throws SGCADBException
     */
    public boolean checkIn(HttpServletRequest request,
            HttpServletResponse response) {
        boolean status = false;
        int id = Integer.parseInt(request.getParameter("id"));
        int roll = (int) request.getSession().getAttribute("roll");
        Date date = java.sql.Date.valueOf(request.getParameter("date"));
        System.out.println(date);
        CheckInBO checkObj = new CheckInBO();
        // If check in is successful
        if (checkObj.checkIn(id, roll, date) == true) {
            status = true;
        }
        // If check in is unsuccessful
        else {
            status = false;
        }
        return status;
    }

}

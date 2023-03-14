package com.work.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import com.work.model.*;
import com.work.bo.*;
import com.work.dao.CheckOutDAO;
import java.util.*;

/**
 * Servlet implementation class CheckOutServlet
 */
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    ArrayList<Book> addBookList;

    public void init() {
        addBookList = new ArrayList<Book>();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");

        // set default next Page as error.jsp
        String nextViewPage = "error.jsp";

        switch (data) {
        // if the action requested is for checkStudent

        case "checkStudent":
            boolean status = checkStudent(request, response);
            if (status) {
                nextViewPage = "/checkoutBookSearch.jsp";
            } else {
                nextViewPage = "/error.jsp?q=s";
            }
            break;

        // if the action requested is for search book
        case "searchBook":
            status = searchBook(request, response);
            if (status == true) {
                nextViewPage = "/checkoutadd.jsp";
            } else {
                nextViewPage = "/error.jsp?q=b";
            }
            break;

        // adding book to cart
        case "addBook":
            status = addBook(request, response);
            nextViewPage = "/checkoutadd.jsp";
            break;

        // checkout of added books
        case "checkout":
            status = checkout(request, response);
            if (status) {
                addBookList.clear();
                nextViewPage = "/success.jsp?q=Checked out Successfully";
            } else {

                nextViewPage = "/error.jsp?q=e";
            }
            break;
            
        //cancel books in list
        case "cancel":
            addBookList.clear();
            nextViewPage = "/checkoutBookSearch.jsp";
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
     * This method checks if the user exists
     * 
     * @param request  An object which is given by tomcat and contains all the
     *                 data passed from client side
     * @param response An object which is given by tomcat to return the data
     * @return boolean This returns a boolean indicating the status of the
     *         method
     */
    private boolean checkStudent(HttpServletRequest request,
            HttpServletResponse response) {
        boolean status = false;
        int rollNo = Integer.parseInt(request.getParameter("rollNo"));
        CheckOutBO Bo = new CheckOutBO();
        Student stud = Bo.getStudentInfo(rollNo);
        // If student is found
        if (stud != null) {
            status = true;
            request.getSession().setAttribute("user", stud);
            request.getSession().setAttribute("name", stud.getName());

            /*
             * ArrayList<Book> ls = Bo.getBooks(); //If list is empty if
             * (ls.isEmpty()) {
             * 
             * } //If the list is not empty else {
             * request.setAttribute("bklist", ls); }
             */

        }
        // If student is not found
        else {
            status = false;
        }
        return status;
    }

    /**
     * This method checks if the given book exists
     * 
     * @param request  An object which is given by tomcat and contains all the
     *                 data passed from client side
     * @param response An object which is given by tomcat to return the data
     * @return boolean This returns a boolean indicating the status of the
     *         method
     */
    private boolean searchBook(HttpServletRequest request,
            HttpServletResponse response) {
        boolean status = false;
        int ISBN = Integer.parseInt(request.getParameter("ISBN"));
        CheckOutBO Bo = new CheckOutBO();
        Book bookObj = Bo.getBookInfo(ISBN);
        // If the book is found
        if (bookObj != null) {
            request.getSession().setAttribute("booklist", bookObj);
            request.setAttribute("C1", bookObj);
            status = true;
        }
        // If the book is not found
        else {
            status = false;
        }
        return status;
    }

    /**
     * This method adds the book into the checkout array list
     * 
     * @param request  An object which is given by tomcat and contains all the
     *                 data passed from client side
     * @param response An object which is given by tomcat to return the data
     * @return boolean This returns a boolean indicating the status of the
     *         method
     */
    private boolean addBook(HttpServletRequest request,
            HttpServletResponse response) {

        Book bookObj = (Book) request.getSession().getAttribute("booklist");
        request.getSession().setAttribute("bookk", bookObj);
        addBookList.add(bookObj);
        Iterator<Book> it = addBookList.iterator();
        while (it.hasNext()) {
            Book s = it.next();
            System.out.println(s.bookName);
        }
        request.getSession().setAttribute("addedList", addBookList);
        boolean status = true;
        return status;
    }

    /**
     * This method inserts data into the database while user checks out
     * 
     * @param request  An object which is given by tomcat and contains all the
     *                 data passed from client side
     * @param response An object which is given by tomcat to return the data
     * @return boolean This returns a boolean indicating the status of the
     *         method
     */
    private boolean checkout(HttpServletRequest request,
            HttpServletResponse response) {
        boolean status = false;
        Student stud = (Student) request.getSession().getAttribute("user");
        ArrayList<Book> list = (ArrayList<Book>) request.getSession()
                .getAttribute("checkoutlist");
        CheckOutBO checkObj = new CheckOutBO();
        // If checkout is successful
        if (checkObj.checkOut(stud, list)) {
            status = true;
        }
        // If checkout is unsuccessful
        else {
            status = false;
        }
        return status;
    }

}

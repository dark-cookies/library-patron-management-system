package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.work.bo.*;
import com.work.model.*;

/**
 * Servlet implementation class NonCheckOut
 */
public class NonCheckOut extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NonCheckOut() {
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
        String nextViewPage = "error.jsp";
        NonCheckBO Bo = new NonCheckBO();
        // Taking list of student who hasn't even seen library
        ArrayList<Student> nonList = Bo.nonList();

        // if list is not null
        if (nonList != null) {
            Iterator<Student> it = nonList.iterator();
            Student studObj = null;
            while (it.hasNext()) {
                studObj = it.next();
                System.out.println(studObj.getName());
            }
            request.setAttribute("nonlist", nonList);
            nextViewPage = "/noncheckout.jsp";
        } 
        //if list is null
        else {
            nextViewPage = "/error.jsp?q=n";
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

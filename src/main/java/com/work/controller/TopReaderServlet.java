package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.work.bo.ReaderBO;
import com.work.exception.SGCADBException;
import com.work.model.Student;

/**
 * Servlet implementation class TopReaderServlet
 */
public class TopReaderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopReaderServlet() {
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
        String next = "/error.jsp";

        ReaderBO Bo = new ReaderBO();
        ArrayList<Student> topReader = Bo.topReaders();
        
        //If the list is empty
        if (topReader.isEmpty()) {
            next = "/error.jsp?q=r";
        } 
        //If list is not empty
        else {
            Iterator<Student> it = topReader.iterator();
            Student studObj = null;
            while (it.hasNext()) {
                studObj = it.next();
                System.out.println(studObj.getName());
            }
            request.setAttribute("toplist", topReader);
            next = "/topreaders.jsp";
        }
        request.getRequestDispatcher(next).forward(request, response);

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

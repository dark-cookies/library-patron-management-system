package com.work.controller;

import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.work.bo.OverdueBO;
import com.work.model.Book;

/**
 * Servlet implementation class OverdueServlet
 */
public class OverdueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OverdueServlet() {
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
        String action = request.getParameter("action");

        switch (action) {
        // For viewing the overdue list
        case "view":
            boolean status = View(request, response);
            if (status) {
                next = "/overduelist.jsp";
            } else {
                next = "/error.jsp?q=o";
            }
            break;

        // For saving the data into Excel document
        case "print":
            status = Download(request, response);
            if (status) {
                next = "/success.jsp?q=Excel saved to disk ";
            } else {
                next = "/OverdueServlet?action=view";
            }
            break;
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

    /**
     * This method checks whether there is overdue
     * 
     * @param request  An object which is given by tomcat and contains all the
     *                 data passed from client side
     * @param response An object which is given by tomcat to return the data
     * @return boolean This returns a boolean indicating the status of the
     *         method
     */
    private boolean View(HttpServletRequest request,
            HttpServletResponse response) {
        boolean status = false;
        OverdueBO Bo = new OverdueBO();
        // checking for any overdue
        ArrayList<Book> List = Bo.getOverdueList();
        System.out.println(List);
        // if no overdue
        if (List.isEmpty()) {
            status = false;
        }
        // if there is overdue
        else {
            Iterator<Book> it = List.iterator();
            Book bookObj = null;
            while (it.hasNext()) {
                bookObj = it.next();
                System.out.println(bookObj.getBookName());
                System.out.println(bookObj.student.getRollNumber());
            }
            request.setAttribute("overduelist", List);
            status = true;
        }
        return status;
    }

    /**
     * This method creates the Excel document which the data will be exported to
     */
    private void printList(String path) {
        try {
            OverdueBO Bo = new OverdueBO();
            ArrayList<Book> List = Bo.getOverdueList();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("sheet1");
            int rownum = 0;
            Row row = sheet.createRow(rownum++);
            createListt(row);
            for (Book bookobj : List) {
                row = sheet.createRow(rownum++);
                createList(bookobj, row);
            }
            FileOutputStream out = new FileOutputStream(
                    new File(path + ".xlsx"));
            workbook.write(out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method creates headings for data in the excel document
     * 
     * @param row Used to specify the different possible policies if for the
     *            case of null and blank cells
     */
    private static void createListt(Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue("Student Name");

        cell = row.createCell(1);
        cell.setCellValue("Roll Number");

        cell = row.createCell(2);
        cell.setCellValue("Book Name");

        cell = row.createCell(3);
        cell.setCellValue("ISBN");

        cell = row.createCell(4);
        cell.setCellValue("Checkout Date");

        cell = row.createCell(5);
        cell.setCellValue("Checkin Date");

        cell = row.createCell(6);
        cell.setCellValue("Overdue days");
    }

    /**
     * This method adds data values to the Excel document
     * 
     * @param book This parameter contains the book object
     * @param row  Used to specify the different possible policies if for the
     *             case of null and blank cells
     */
    private static void createList(Book book, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(book.student.getName());

        cell = row.createCell(1);
        cell.setCellValue(book.student.getRollNumber());

        cell = row.createCell(2);
        cell.setCellValue(book.getBookName());

        cell = row.createCell(3);
        cell.setCellValue(book.getISBN());

        cell = row.createCell(4);
        Date checkout = book.getCheckOutDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String checkoutdate = dateFormat.format(checkout);
        cell.setCellValue(checkoutdate);

        cell = row.createCell(5);
        Date checkin = book.getDueDate();
        String checkindate = dateFormat.format(checkin);
        cell.setCellValue(checkindate);

        cell = row.createCell(6);
        cell.setCellValue(book.student.getOverdueDays());

    }

    /**
     * This method browse the directory to save the excel document
     * @param request  An object which is given by tomcat and contains all the
     *                 data passed from client side
     * @param response An object which is given by tomcat to return the data
     * @return boolean This returns a boolean indicating the status of the
     *         method
     */
    private boolean Download(HttpServletRequest request,
            HttpServletResponse response) {
        boolean status = false;
        JFrame parentFrame = new JFrame();
        String path = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        FileFilter filter = new FileNameExtensionFilter("MS Excel Document",
                ".xlsx"); // filter to show only that
        fileChooser.setAcceptAllFileFilterUsed(false); // to show or not all
                                                       // other files
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setSelectedFile(new File(path));
        fileChooser.setVisible(true);
        parentFrame.toFront();
        parentFrame.setAlwaysOnTop(true);

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            path = fileToSave.getAbsolutePath();
            printList(path);
            status = true;
        } else if (userSelection == JFileChooser.CANCEL_OPTION) {
            status = false;
        }
        return status;
    }

}

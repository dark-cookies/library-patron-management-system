package com.work.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;

import com.work.exception.*;
import com.work.model.*;

public class OverdueDAO {

    /**
     * This method checks for overdue
     * 
     * @return list This returns the list of overdue
     */
    public ArrayList<Book> getOverdueList() throws SGCADBException {
        Book bookobj = null;
        ArrayList<Book> list = new ArrayList<Book>();
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM CHECKOUT WHERE CHECKIN_DATE IS NULL "
                    + "ORDER BY CHECKOUT_DATE";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                bookobj = new Book();
                bookobj.setBookName(rs.getString("BOOK_NAME"));
                bookobj.setBookID(rs.getInt("BOOKID"));
                bookobj.setISBN(rs.getInt("ISBN"));
                bookobj.setCheckOutDate(rs.getDate("CHECKOUT_DATE"));
                bookobj.student.setName(rs.getString("STUDENT_NAME"));
                bookobj.student.setRollNumber(rs.getInt("ROLLNO"));
                bookobj.student.setCourse(rs.getString("COURSE"));
                Date date1 = rs.getDate("CHECKOUT_DATE");
                Date date2 = java.sql.Date.valueOf(java.time.LocalDate.now());
                Date startDate = date1;
                Date endDate = date2;
                long duration = endDate.getTime() - startDate.getTime();
                long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
                if (diffInDays >= 14) {
                    int over = (int) diffInDays - 14;
                    LocalDate date3 = date1.toLocalDate();
                    ;
                    LocalDate date4 = date3.plusDays(14);
                    bookobj.setDueDate(java.sql.Date.valueOf(date4));
                    bookobj.student.setOverdueDays(over);
                    list.add(bookobj);
                }
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SGCADBException(e);
        } finally{
            DBConnection.closeConnection(con);
        }
        return list;
    }

}

package com.work.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.work.exception.*;
import com.work.model.Book;

public class PatronSearchDAO {

    /**
     * This method retrieves the checkout history of the user specified
     * 
     * @param rollNo This parameter contains the roll no of user to be searched
     * @return list This returns the array list with the checkout details
     */
    public ArrayList<Book> patronList(int rollNo) throws SGCADBException {
        Book book = null;
        ArrayList<Book> list = new ArrayList<Book>();
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM CHECKOUT WHERE ROLLNO = " + rollNo
                    + " ORDER BY CHECKOUT_DATE,CHECKIN_DATE";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                book = new Book();
                book.setBookName(rs.getString("BOOK_NAME"));
                book.setBookID(rs.getInt("BOOKID"));
                book.setISBN(rs.getInt("ISBN"));
                book.setCheckOutDate(rs.getDate("CHECKOUT_DATE"));
                book.setCheckInDate(rs.getDate("CHECKIN_DATE"));
                list.add(book);
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

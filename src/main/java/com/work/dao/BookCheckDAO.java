package com.work.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.work.exception.SGCADBException;
import com.work.model.*;

public class BookCheckDAO {

    public BookCheckDAO() {
        // TODO Auto-generated constructor stub
    }
    Connection con = null;
    /**
     * This method search whether the book is available for checkout
     * 
     * @param ISBN This parameter contains the ISBN of the Book to be searched
     * @return Book This returns the book object, if found.
     */
    public Book getBookInfo(int ISBN) throws SGCADBException {
        
        Book bookObj = null;
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK WHERE ISBN = "
                    + ISBN + " AND AVAILABLE=TRUE");
            if (rs.next()) {
                bookObj = new Book();
                bookObj.setBookName(rs.getString("BOOKNAME"));
                bookObj.setBookID(rs.getInt("BOOKID"));
                bookObj.setISBN(rs.getInt("ISBN"));
                bookObj.setAuthor(rs.getString("AUTHOR"));
                bookObj.setPublisher(rs.getString("PUBLISHER"));
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SGCADBException(e);
        } finally{
            DBConnection.closeConnection(con);
        }
        return bookObj;
    }

    /**
     * This method checks if the user has due
     * 
     * @param rollNo The parameter contains roll no of the user to be searched
     * @return list This returns an  array list containing the book details
     */
    public ArrayList<Book> getList(int rollNo) throws SGCADBException{
        Book bookobj = null;
        ArrayList<Book> list = new ArrayList<Book>();
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM CHECKOUT WHERE ROLLNO = " + rollNo
                    + " AND CHECKIN_DATE IS NULL";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                bookobj = new Book();
                bookobj.setBookName(rs.getString("BOOK_NAME"));
                bookobj.setBookID(rs.getInt("BOOKID"));
                bookobj.setISBN(rs.getInt("ISBN"));
                bookobj.setCheckOutDate(rs.getDate("CHECKOUT_DATE"));
                list.add(bookobj);
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

    /**
     * This method finds all the available books for checkout
     * 
     * @return list This returns the list of available books
     */
    public ArrayList<Book> getBooks() throws SGCADBException{
        Book bookObj = null;
        ArrayList<Book> list = new ArrayList<Book>();
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT * FROM BOOK WHERE AVAILABLE=TRUE");
            while (rs.next()) {
                bookObj = new Book();
                bookObj.setBookName(rs.getString("BOOKNAME"));
                bookObj.setBookID(rs.getInt("BOOKID"));
                bookObj.setISBN(rs.getInt("ISBN"));
                bookObj.setAuthor(rs.getString("AUTHOR"));
                bookObj.setPublisher(rs.getString("PUBLISHER"));
                list.add(bookObj);
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

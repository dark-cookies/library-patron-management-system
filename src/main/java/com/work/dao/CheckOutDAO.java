package com.work.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.work.model.*;
import com.work.dao.DBConnection;
import java.util.ArrayList;
import java.util.Iterator;
import com.work.exception.*;
public class CheckOutDAO {

    /**
     * This method inserts records into the database when user checks out
     * 
     * @param stud This parameter contains the student object of the user
     * @param list This parameter contains the list of books for checkout
     * @return boolean This returns a boolean which indicate the status of the
     *         method
     */
    public boolean checkOut(Student stud, ArrayList<Book> list) 
                                                         throws SGCADBException{
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            Iterator<Book> it = list.iterator();
            while (it.hasNext()) {
                Book bookObj = it.next();
                PreparedStatement pst = con.prepareStatement(
                        "INSERT INTO CHECKOUT(ROLLNO,STUDENT_NAME,COURSE,ISBN,"
                        + "BOOK_NAME,BOOKID,CHECKOUT_DATE,CHECKIN_DATE)"
                        + " VALUES (?,?,?,?,?,?,?,?);");
                pst.setInt(1, stud.rollNo);
                pst.setString(2, stud.name);
                pst.setString(3, stud.course);
                pst.setInt(4, bookObj.ISBN);
                pst.setString(5, bookObj.bookName);
                pst.setInt(6, bookObj.bookId);
                pst.setDate(7,
                        java.sql.Date.valueOf(java.time.LocalDate.now()));
                pst.setDate(8, null);
                pst.execute();
                pst.close();
                
                PreparedStatement pst1 = con.prepareStatement(
                        "UPDATE USER SET BOOKCOUNT=BOOKCOUNT+1 WHERE ROLLNO="
                                + stud.rollNo);
                pst1.execute();
                pst1.close();
                
                PreparedStatement pst2 = con.prepareStatement(
                        "UPDATE BOOK SET AVAILABLE=FALSE WHERE ISBN="
                                + bookObj.ISBN);
                pst2.execute();
                pst2.close();
            }
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SGCADBException(e);
            
        } finally{
            DBConnection.closeConnection(con);
        }
    }
}

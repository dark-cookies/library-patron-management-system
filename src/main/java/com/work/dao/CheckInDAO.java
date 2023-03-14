package com.work.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.work.model.*;
import com.work.exception.*;
import com.work.dao.DBConnection;

public class CheckInDAO {

    /**
     * This method inserts records into database when user checks in a book
     * 
     * @param id       This parameter contains the id of the book
     * @param rollNo   This parameter contains roll no of the user
     * @param checkout This parameter contains checkout date of the book
     * @return boolean This returns a boolean indicating the status of check in
     *         operation
     */
    public boolean checkIn(int id, int rollNo, Date checkout)
                                                        throws SGCADBException {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
            String query = "UPDATE CHECKOUT SET CHECKIN_DATE='" + date
                    + "' WHERE CHECKOUT_DATE='" + checkout + "' AND ROLLNO="
                    + rollNo + " AND BOOKID=" + id;
            PreparedStatement pst = con.prepareStatement(query);
            pst.execute();
            pst.close();

            PreparedStatement pst2 = con.prepareStatement(
                    "UPDATE BOOK SET AVAILABLE=TRUE WHERE BOOKID=" + id);
            pst2.execute();
            pst2.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SGCADBException(e);
        } finally {
            DBConnection.closeConnection(con);
        }
    }

}

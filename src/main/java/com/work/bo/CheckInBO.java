package com.work.bo;

import com.work.exception.*;
import com.work.dao.*;
import com.work.model.*;

import java.sql.Date;
import java.util.*;

/**
 * 
 * @author Johns Sebastian
 *
 */
public class CheckInBO {

    /**
     * This method checks if the user has due
     * 
     * @param rollNo The parameter contains roll no of the user to be searched
     * @return list This returns an array list containing the book details
     */
    public ArrayList<Book> getList(int rollNo) {
        ArrayList<Book> list = null;
        BookCheckDAO dao = new BookCheckDAO();
        try {
            list = dao.getList(rollNo);
        } catch (SGCADBException e) {
            list = null;
        }
        return list;
    }

    /**
     * This method inserts records into database when user checks in a book
     * 
     * @param id       This parameter contains the id of the book
     * @param rollNo   This parameter contains roll no of the user
     * @param checkout This parameter contains checkout date of the book
     * @return boolean This returns a boolean indicating the status of check in
     *         operation
     */
    public boolean checkIn(int id, int rollNo, Date checkout) {
        CheckInDAO dao = new CheckInDAO();
        boolean status = false;
        try {
            status = dao.checkIn(id, rollNo, checkout);
        } catch (SGCADBException e) {
            status = false;
        }
        return status;
    }

}

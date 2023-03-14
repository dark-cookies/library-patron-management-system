package com.work.bo;

import com.work.dao.*;
import com.work.exception.SGCADBException;
import com.work.model.*;

import java.sql.Date;
import java.util.*;

/**
 * 
 * @author Johns Sebastian
 *
 */
public class CheckOutBO {

    /**
     * This method checks whether the student exists or not
     * 
     * @param rollNo This parameter contains the roll no of the student to be
     *               searched
     * @return student This returns the student object if found
     */
    public Student getStudentInfo(int rollNo) {
        SearchDAO dao = new SearchDAO();
        Student obj = null;
        try {
            obj = dao.getStudentInfo(rollNo);
        } catch (SGCADBException e) {

        }
        return obj;
    }

    /**
     * This method search whether the book is available for checkout
     * 
     * @param ISBN This parameter contains the ISBN of the Book to be searched
     * @return Book This returns the book object, if found.
     */
    public Book getBookInfo(int ISBN) {
        BookCheckDAO dao = new BookCheckDAO();
        Book obj = null;
        try {
            obj = dao.getBookInfo(ISBN);
        } catch (SGCADBException e) {

        }
        return obj;
    }

    /**
     * This method finds all the available books for checkout
     * 
     * @return list This returns the list of available books
     */
    public ArrayList<Book> getBooks() {
        BookCheckDAO dao = new BookCheckDAO();
        ArrayList<Book> list = null;
        try {
            list = dao.getBooks();
        } catch (SGCADBException e) {

        }
        return list;
    }

    /**
     * This method inserts records into the database when user checks out
     * 
     * @param stud This parameter contains the student object of the user
     * @param list This parameter contains the list of books for checkout
     * @return boolean This returns a boolean which indicate the status of the
     *         method
     */
    public boolean checkOut(Student stud, ArrayList<Book> list) {
        CheckOutDAO dao = new CheckOutDAO();
        boolean status = false;
        try {
            status = dao.checkOut(stud, list);
        } catch (SGCADBException e) {

        }
        return status;

    }

}

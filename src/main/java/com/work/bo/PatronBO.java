package com.work.bo;

import java.util.ArrayList;

import com.work.dao.PatronSearchDAO;
import com.work.dao.SearchDAO;
import com.work.exception.SGCADBException;
import com.work.model.Book;
import com.work.model.Student;

/**
 * 
 * @author Johns Sebastian
 *
 */
public class PatronBO {

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
     * This method retrieves the checkout history of the user specified
     * 
     * @param rollNo This parameter contains the roll no of user to be searched
     * @return This returns the array list with the checkout details
     */
    public ArrayList<Book> patronList(int rollNo) {
        PatronSearchDAO dao = new PatronSearchDAO();
        ArrayList<Book> list = null;
        try {
            list = dao.patronList(rollNo);
        } catch (SGCADBException e) {

        }
        return list;

    }

}

package com.work.bo;

import java.util.ArrayList;

import com.work.dao.TopReadersDAO;
import com.work.exception.SGCADBException;
import com.work.model.Student;

/**
 * 
 * @author Johns Sebastian
 *
 */
public class ReaderBO {

    /**
     * This method find the users with highest number of books read
     * 
     * @return list This returns an array list of the top readers
     */
    public ArrayList<Student> topReaders() {
        TopReadersDAO dao = new TopReadersDAO();
        ArrayList<Student> list = null;
        try {
            list = dao.topReaders();
        } catch (SGCADBException e) {

        }
        return list;
    }

}

package com.work.bo;

import java.util.ArrayList;

import com.work.dao.NonCheckDAO;
import com.work.exception.SGCADBException;
import com.work.model.Student;

/**
 * 
 * @author Johns Sebastian
 *
 */
public class NonCheckBO {

    /**
     * This method checks for users with no checkouts
     * 
     * @return list This returns an array list of users
     */
    public ArrayList<Student> nonList() {
        NonCheckDAO dao = new NonCheckDAO();
        ArrayList<Student> list = null;
        try {
            list = dao.nonList();
        } catch (SGCADBException e) {

        }
        return list;
    }

}

package com.work.bo;

import java.util.ArrayList;

import com.work.dao.OverdueDAO;
import com.work.exception.SGCADBException;
import com.work.model.Book;

/**
 * 
 * @author Johns Sebastian
 *
 */
public class OverdueBO {

    /**
     * This method checks for overdue
     * 
     * @return list This returns the list of overdue
     */
    public ArrayList<Book> getOverdueList() {
        OverdueDAO dao = new OverdueDAO();
        ArrayList<Book> list = null;
        try {
            list = dao.getOverdueList();
        } catch (SGCADBException e) {

        }
        return list;
    }
}

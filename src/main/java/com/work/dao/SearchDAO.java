package com.work.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.work.exception.*;
import com.work.model.*;

public class SearchDAO {

    public SearchDAO() {
        // TODO Auto-generated constructor stub
    }

    /**
     * This method checks whether the student exists or not
     * 
     * @param rollNo This parameter contains the roll no of the student to be
     *               searched
     * @return student This returns the student object if found
     */
    public Student getStudentInfo(int rollNo) throws SGCADBException{
        Student stud = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM USER WHERE ROLLNO = " + rollNo);
            if (rs.next()) {
                stud = new Student();
                stud.setName(rs.getString("NAME"));
                stud.setRollNumber(rs.getInt("ROLLNO"));
                stud.setCourse(rs.getString("COURSE"));
                stud.setEmail(rs.getString("EMAIL"));
                stud.setBookCount(rs.getInt("BOOKCOUNT"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SGCADBException(e);
        } finally{
            DBConnection.closeConnection(con);
        }
        return stud;
    }

}

package com.work.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.work.exception.*;
import com.work.model.*;

public class NonCheckDAO {

    /**
     * This method checks for users with no checkouts
     * 
     * @return list This returns an array list of users
     */
    public ArrayList<Student> nonList() throws SGCADBException{
        Student stud = null;
        ArrayList<Student> list = new ArrayList<Student>();
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT * FROM USER WHERE BOOKCOUNT=0");
            while (rs.next()) {
                stud = new Student();
                stud.setName(rs.getString("NAME"));
                stud.setRollNumber(rs.getInt("ROLLNO"));
                stud.setCourse(rs.getString("COURSE"));
                stud.setEmail(rs.getString("EMAIL"));
                list.add(stud);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SGCADBException(e);
        } finally{
            DBConnection.closeConnection(con);
        }
        return list;
    }

}

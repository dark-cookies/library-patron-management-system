package com.work.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.work.exception.*;
import com.work.model.Student;

public class TopReadersDAO {

    public TopReadersDAO() {
        // TODO Auto-generated constructor stub
    }

    /**
     * This method find the users with highest number of books read
     * 
     * @return list This returns an array list of the top readers
     */
    public ArrayList<Student> topReaders() throws SGCADBException {
        Student stud = null;
        Connection con = null;
        ArrayList<Student> list = new ArrayList<Student>();
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM USER WHERE BOOKCOUNT>0 ORDER BY BOOKCOUNT DESC");
            while (rs.next()) {
                stud = new Student();
                stud.setName(rs.getString("NAME"));
                stud.setRollNumber(rs.getInt("ROLLNO"));
                stud.setCourse(rs.getString("COURSE"));
                stud.setEmail(rs.getString("EMAIL"));
                stud.setBookCount(rs.getInt("BOOKCOUNT"));
                list.add(stud);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SGCADBException(e);
        } finally {
            DBConnection.closeConnection(con);
        }
        return list;
    }
}

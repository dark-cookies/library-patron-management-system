package com.work.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.work.exception.*;

public class DBConnection {

    public DBConnection() {
        // TODO Auto-generated constructor stub
    }

    /**
     * This method creates a connection to the database
     * 
     * @return connection This method returns the connection object
     * @throws SGCADBException
     */
    public static Connection getConnection() throws SGCADBException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pro",
                    "root", "root");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SGCADBException(e);
        }
        return con;
    }

    /**
     * This method closes the connection to the database
     * 
     * @param con This parameter contains the connection object
     * @throws SGCADBException
     */
    public static void closeConnection(Connection con) throws SGCADBException {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new SGCADBException(e);
            }

        }
    }
}

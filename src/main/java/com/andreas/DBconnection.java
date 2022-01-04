package com.andreas;
import java.sql.*;

public class DBconnection {
    Connection con;
    Statement stmt;

    DBconnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankdb", "user", "password");
                stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

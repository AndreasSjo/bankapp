package com.andreas;
import java.sql.*;

public class DBconnection {
    Connection connection;
    Statement statement;

    DBconnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankdb", "user", "password");
                statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

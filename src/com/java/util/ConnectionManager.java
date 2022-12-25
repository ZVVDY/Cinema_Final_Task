package com.java.util;

import java.sql.*;

public class ConnectionManager {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3307/cinema";
    private static String username = "root";
    private static String password = "";
    private static Connection connect;


    public void connectionBase() throws ClassNotFoundException {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connection server");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection MySQL");
        }
    }
}

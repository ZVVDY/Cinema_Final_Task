package com.java.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

@Slf4j
public class ConnectionManager {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3307/cinema";
    private static String username = "root";
    private static String password = "";
    private static Connection connect;
    private Date date = new Date();

    public void connectionBase() throws ClassNotFoundException {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connection server");
            log.info("Подключение к серверу успешно установлено "+date);
        } catch (SQLException e) {
            System.err.println("Нет подключения к базе данных, программа может работать не коректно" +
                    "(No connection MySQL) " + date);
            log.error("Нет подключения к базе данных, программа может работать не коректно" +
                    "(No connection MySQL) " + date);
        }
    }
}

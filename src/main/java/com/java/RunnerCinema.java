package com.java;


import com.java.controller.GeneralController;
import com.java.util.ConnectionManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class RunnerCinema {
    public static Date date = new Date();
    public static void main(String[] args) {
        try {
            ConnectionManager connectionManager = new ConnectionManager();
            connectionManager.connectionBase();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            System.err.println("Нет подключения к базе данных(No connection Base)");
            log.error("Нет подключения к Базе данных(No connection Base)" + date);
        }
        try {
            GeneralController cinemaController = new GeneralController();
            cinemaController.menuCinema();
        } catch (ClassNotFoundException e) {
            System.err.println("No class foud exception");
        }
    }
}
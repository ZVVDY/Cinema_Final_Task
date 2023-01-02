package com.java;


import com.java.controller.GeneralController;
import com.java.util.ConnectionManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnerCinema {
    public static void main(String[] args) {
        try {
            ConnectionManager connectionManager = new ConnectionManager();
            connectionManager.connectionBase();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            System.err.println("No connection Base");
            log.error("No connection Base");
        }
        try {
            GeneralController cinemaController = new GeneralController();
            cinemaController.menuCinema();
        } catch (ClassNotFoundException e) {
            System.err.println("No class foud exception");
        }
    }
}
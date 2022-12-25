package com.java;


import com.java.controller.GeneralController;
import com.java.util.ConnectionManager;


public class RunnerCinema {
    public static void main(String[] args) throws ClassNotFoundException {
//        ConnectionManager connectionManager=new ConnectionManager();
//        connectionManager.connectionBase();
        GeneralController cinemaController = new GeneralController();
        cinemaController.menuCinema();

    }
}
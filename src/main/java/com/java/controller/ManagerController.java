package com.java.controller;

import java.util.Date;
import java.util.Scanner;

public class ManagerController {
    Scanner scanner = new Scanner(System.in);
private Date date = new Date();
    protected void menuManagerController(String loginInApp) {
        System.out.println("Вы вошли в приложение под логином: " + loginInApp);
        System.out.println("Дата входа " + date);
        System.out.println("1. Просмотреть доступные фильмы/мероприятия");
        System.out.println("2. Добавить фильмы/мероприятия");
        System.out.println("0. Выход");
        int number = scanner.nextInt();
        switch (number) {
            case 1:
//                filmService.viewEventsAndMovies(loginInApp);
//                scanner.remove();
//                //break;
//            case 2:
//                filmService.createEventsAndMovies();
//            case 0:
//                break;
//            default:
//                System.out.println("Введите номер меню");
//                break;
        }

    }
}

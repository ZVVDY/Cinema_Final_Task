package com.java.controller;

import com.java.service.FilmService;
import com.java.service.FilmServiceImp;
import com.java.service.TicketService;
import com.java.service.TicketServiceImp;

import java.util.Date;
import java.util.Scanner;

public class ManagerController {
    Scanner scanner = new Scanner(System.in);
    private FilmService service = new FilmServiceImp();
    private TicketService ticketService = new TicketServiceImp();
private Date date = new Date();
    protected void menuManagerController(String loginInApp) {
        System.out.println("Вы вошли в приложение под логином: " + loginInApp);
        System.out.println("Дата входа " + date);
        System.out.println("1. Просмотреть доступные фильмы/мероприятия");
        System.out.println("2. Добавить фильмы/мероприятия");
        System.out.println("3. Добавить билеты в фильмы/мероприятия");
        System.out.println("4. Купить / возвратить билеты на фильмы/мероприятия");
        System.out.println("0. Выход");
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                service.viewEventsAndMovies(loginInApp);
                break;
             case 2:
              service.createEventsAndMovies();
              break;
            case 3:
                ticketService.createTicket();
                break;
//            case 0:
//                break;
//            default:
//                System.out.println("Введите номер меню");
//                break;
        }

    }
}

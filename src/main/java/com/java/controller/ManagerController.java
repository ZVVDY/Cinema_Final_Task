package com.java.controller;

import com.java.service.*;

import java.util.Date;
import java.util.Scanner;

public class ManagerController {
    Scanner scanner = new Scanner(System.in);
    private FilmService service = new FilmServiceImp();
    private TicketService ticketService = new TicketServiceImp();
    private PersonService personService = new PersonServiceImp();

    private Date date = new Date();

    protected void menuManagerController(String loginInApp) {
        System.out.println("Вы вошли в приложение под логином: " + loginInApp);
        System.out.println("Дата входа " + date);
        System.out.println("1. Просмотреть доступные фильмы/мероприятия");
        System.out.println("2. Добавить фильмы/мероприятия");
        System.out.println("3. Добавить билеты в фильмы/мероприятия");
        System.out.println("4. Купить  билеты на фильмы/мероприятия для пользователя");
        System.out.println("5. Возвратить билеты на фильмы/мероприятия");
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
            case 4:
                ticketService.buyAMovieTicket(personService.searchForAPersonInTheDatabase());
                break;
            case 5:ticketService.viewPurchasedMovieTickets(personService.searchForAPersonInTheDatabase());
            break;
            case 0:
                break;
            default:
                System.out.println("Введите номер меню");
                break;
        }

    }
}

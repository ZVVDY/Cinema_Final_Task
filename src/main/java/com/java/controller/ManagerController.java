package com.java.controller;

import com.java.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class ManagerController {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private FilmService service = new FilmServiceImp();
    private TicketService ticketService = new TicketServiceImp();
    private PersonService personService = new PersonServiceImp();

    private Date date = new Date();

    protected void menuManagerController(String loginInApp) throws IOException {
        System.out.println("Вы вошли в приложение под логином: (You are logged into the app using:)" + loginInApp);
        System.out.println("Дата входа (Release date) " + date);
        System.out.println("1. Просмотреть доступные фильмы/мероприятия(View available films of the event");
        System.out.println("2. Добавить фильмы/мероприятия (Add MoviesEvents");
        System.out.println("3. Добавить билеты в фильмы/мероприятия (Add Tickets to MoviesEvents)");
        System.out.println("4. Купить  билеты на фильмы/мероприятия для пользователя (Buy event movie tickets for a user) ");
        System.out.println("5. Возвратить билеты на фильмы/мероприятия для пользователя (Refund event movie tickets for a user) ");
        System.out.println("6. Редактировать фильмы/мероприятия  (Edit MoviesEvents) ");
        System.out.println("0. Выход(Exit)");
        int number = Integer.parseInt(reader.readLine());
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
                ticketService.buyAMovieTicket();
                break;
            case 5:
                ticketService.refundMovieTicket(personService.searchForAPersonInTheDatabase());
                break;
            case 6:
                service.editEventsAndMovies();
                break;
            case 0:
                break;
            default:
                System.out.println("Введите номер меню (Enter menu number)");
                break;
        }

    }
}

package com.java.controller;

import com.java.service.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

@Slf4j
public class AdministratorController {
    Scanner scanner = new Scanner(System.in);
    private FilmService filmService = new FilmServiceImp();
    private TicketService ticketService = new TicketServiceImp();
    private PersonService personService = new PersonServiceImp();
    private Date date = new Date();

    protected void menuAdministratorController(String loginInApp) throws ClassNotFoundException, IOException {
        System.out.println("Вы вошли в приложение под логином: (You are logged into the app using:)" + loginInApp);
        System.out.println("Дата входа (Release date) " + date);
        log.info("Пользователь " + loginInApp + " меню администратора,дата и время входа " + date);
        System.out.println("1. Просмотреть доступные фильмы/мероприятия(View available films of the event");
        System.out.println("2. Добавить фильмы/мероприятия (Add MoviesEvents");
        System.out.println("3. Добавить билеты в фильмы/мероприятия (Add Tickets to MoviesEvents)");
        System.out.println("4. Купить  билеты на фильмы/мероприятия для пользователя (Buy event movie tickets for a user) ");
        System.out.println("5. Возвратить билеты на фильмы/мероприятия для пользователя (Refund event movie tickets for a user) ");
        System.out.println("6. Редактировать фильмы/мероприятия  (Edit MoviesEvents) ");
        System.out.println("7. Удалить фильмы/мероприятия  (Delete MoviesEvents) ");
        System.out.println("8. Создать пользователя  (Create user) ");
        System.out.println("9. Удалить пользователя  (Delete user) ");
        System.out.println("10. Редактировать пользователя  (Edit user) ");
        System.out.println("0. Выход(Exit)");
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                filmService.viewEventsAndMovies(loginInApp);
                menuAdministratorController(loginInApp);
                break;
            case 2:
                filmService.createEventsAndMovies();
                menuAdministratorController(loginInApp);
                break;
            case 3:
                ticketService.createTicket();
                menuAdministratorController(loginInApp);
                break;
            case 4:
                ticketService.buyAMovieTicket();
                menuAdministratorController(loginInApp);
                break;
            case 5:
                ticketService.refundMovieTicket(personService.searchForAPersonInTheDatabase());
                menuAdministratorController(loginInApp);
                break;
            case 6:
                filmService.editEventsAndMovies();
                menuAdministratorController(loginInApp);
                break;
            case 7:
                filmService.deleteEventsAndMovies();
                menuAdministratorController(loginInApp);
                break;
            case 8:
                personService.create(personService.createPerson());
                menuAdministratorController(loginInApp);
                break;
            case 9:
                personService.delete();
                menuAdministratorController(loginInApp);
                break;
            case 10:
                personService.update();
                menuAdministratorController(loginInApp);
                break;
            case 0:
                log.info("Пользователь вышел из програмы");
                break;
            default:
                System.out.println("Введите номер меню (Enter menu number)");
                break;
        }

    }
}

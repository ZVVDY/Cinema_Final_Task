package com.java.controller;

import com.java.service.*;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class AdministratorController {
    Scanner scanner = new Scanner(System.in);
    private FilmService service = new FilmServiceImp();
    private TicketService ticketService = new TicketServiceImp();
    private PersonService personService = new PersonServiceImp();

    private Date date = new Date();

    protected void menuAdministratorController(String loginInApp) throws ClassNotFoundException, IOException {
        System.out.println("Вы вошли в приложение под логином: (You are logged into the app using:)" + loginInApp);
        System.out.println("Дата входа (Release date) " + date);
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
            case 7:
                service.deleteEventsAndMovies();//удаление мероприятия
                break;
            case 8:
                PersonController controller = new PersonController();
                controller.registrationPersonInTheAppCinema();
                menuAdministratorController(loginInApp);
                break;
            case 9:
                personService.delete();//удаление пользователя
                break;
            case 10:
                personService.update();//изменение пользователя
                break;
            case 0:
                break;
            default:
                System.out.println("Введите номер меню (Enter menu number)");
                break;
        }

    }
}

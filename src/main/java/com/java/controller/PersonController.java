package com.java.controller;

import com.java.model.Person;
import com.java.model.RoleClient;
import com.java.service.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;

@Slf4j
public class PersonController {
    private PersonService personService = new PersonServiceImp();
    private TicketService ticketService = new TicketServiceImp();
    private FilmService filmService = new FilmServiceImp();
    private Scanner scanner = new Scanner(System.in);
    private Scanner scannerTwo = new Scanner(System.in);
    private Date date = new Date();
    //private String loginRegist;

    public void menuPersonController(String loginInApp) {
        System.out.println("Вы вошли в приложение, используя логин: (You are logged into the app using: )" + loginInApp);
        System.out.println("Дата входа (Release date) " + date);//дата входа
        System.out.println("1. Просмотреть доступные для покупки фильмы/мероприятия(View events available for purchase of the film)");
        System.out.println("2. Просмотреть фильмы/мероприятия (Watch  Movies and Events) ");
        System.out.println("3. Посмотреть купленные фильмы/мероприятия (View purchased films of the event)");
        System.out.println("4. Создать мероприятие");
        System.out.println("0. Выход (Exit)");
        int number = scannerTwo.nextInt();
        switch (number) {
            case 1:
                filmService.viewEventsAndMovies(loginInApp);
                break;
            case 2:
                ticketService.viewPurchasedMovieTickets(loginInApp);
                break;
            case 3:
                ticketService.refundMovieTicket(loginInApp);
                break;
            case 4:filmService.createEventsAndMovies();
            break;
            case 0:
                break;
            default:
                System.out.println("Enter menu number");//ведите номер меню
                break;
        }

    }

    protected void logInToTheAppCinema() throws ClassNotFoundException {

        GeneralController generalController = new GeneralController();
        System.out.println("Вход в приложение (Application Login)");
        System.out.println("Введите логин пользователя (Enter username)");
        String loginPersonRead = scanner.nextLine();
        if (personService.readLogin(loginPersonRead)) {
            System.out.println("Введите пароль пользователя (Enter user password)");
            String passwordPersonRead = scanner.nextLine();
            boolean toLogAndPasswordRegist = personService.readPasswordAddLog(loginPersonRead, passwordPersonRead);
            //personService.seachRole(loginPersonRead,passwordPersonRead);
            if (toLogAndPasswordRegist) {
                menuPersonController(loginPersonRead);
            }
            if (!toLogAndPasswordRegist) {
                logInToTheAppCinema();
            }
        } else {
            System.err.println("Данного логина не существует, пройдите регистрацию (This username does not exist, please register)");
            generalController.menuCinema();
        }
    }

    protected void registrationPersonInTheAppCinema() throws ClassNotFoundException {
        String loginPerson;
        String passwordPerson;
        GeneralController generalController = new GeneralController();
        Person person = new Person();
        int numberLogin = 0;
        int numberPassword = 0;
        while (numberLogin < 3) {
            System.out.println("Введите логин пользователя (Enter username)");
            loginPerson = scanner.nextLine();
            //loginRegist = loginPerson;
            if (loginPerson.isEmpty()) {
                System.out.println("Вы не ввели логин (You have not entered a login) " + (numberLogin + 1) + " раз (once)");
                numberLogin++;
            }
            if (!loginPerson.isEmpty()) {
                if (personService.readLogin(loginPerson)) {
                    System.out.println("Такой пользователь есть в базе (Such a user exists in the database)");

                } else {
                    person.setLoginPerson(loginPerson);
                    System.out.println("Логин введен (Login entered)");
                    break;
                }
            }
            if (numberLogin == 3) {
                System.out.println("Колличество попыток закончилось пройдите регистрацию заново (The number of attempts has ended, please register again)");
                generalController.menuCinema();
            }
        }
        while (numberPassword < 3) {
            System.out.println("Введите пароль пользователя (Enter user password)");
            passwordPerson = scanner.nextLine();
            if (passwordPerson.isEmpty()) {
                System.err.println("Вы не ввели пароль (You have not entered a password )" + (numberPassword + 1) + "раз (once)");
                numberPassword++;
            }
            if (!passwordPerson.isEmpty()) {
                person.setPasswordPerson(passwordPerson);
                personService.create(person);
                System.out.println("Вы успешно зарегистрированы (You have successfully registered)");
                log.info("Успешная зарегистрация под логином " + person.getLoginPerson());
                generalController.menuCinema();
                scanner.remove();
                scannerTwo.remove();
            }
            if (numberPassword == 3) {
                System.err.println("Колличество попыток ввода пароля закончилось пройдите регистрацию заново (The number of password attempts has expired, please register again)");
                generalController.menuCinema();
            }
        }

    }

}

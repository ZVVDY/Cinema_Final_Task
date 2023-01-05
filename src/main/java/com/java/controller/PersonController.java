package com.java.controller;

import com.java.model.Person;
import com.java.model.RoleClient;
import com.java.service.*;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

@Slf4j
public class PersonController {
    private PersonService personService = new PersonServiceImp();
    private TicketService ticketService = new TicketServiceImp();
    private FilmService filmService = new FilmServiceImp();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Date date = new Date();
    private ManagerController managerController = new ManagerController();
    private AdministratorController administratorController = new AdministratorController();

    public void menuPersonController(String loginInApp) throws ClassNotFoundException, IOException {
        System.out.println("Вы вошли в приложение, используя логин: (You are logged into the app using: )" + loginInApp);
        System.out.println("Дата входа (Release date) " + date);
        log.info("Пользователь " + loginInApp + " открыл главное меню,дата и время входа " + date);
        System.out.println("1. Покупка билетов на фильмы/мероприятия(Purchasing tickets for films)");
        System.out.println("2. Посмотреть фильмы мероприятия в кинотеатре (Watch movies events at the cinema) ");
        System.out.println("3. Вернуть купленные фильмы/мероприятия (View purchased films of the event)");
        System.out.println("4. Просмотреть  купленные билеты фильмы/мероприятия (View purchased tickets films of the event)");
        System.out.println("0. Выход (Exit)");
        int number = Integer.parseInt(reader.readLine());
        switch (number) {
            case 1:
                filmService.viewEventsAndMovies(loginInApp);
                menuPersonController(loginInApp);
                break;
            case 2:
                filmService.watchMoviesEventsAtTheCinema(loginInApp);
                menuPersonController(loginInApp);
                break;
            case 3:
                ticketService.refundMovieTicket(loginInApp);
                menuPersonController(loginInApp);
                break;
            case 4:
                ticketService.viewPurchasedMovieTickets(loginInApp);
            case 0:
                log.info("Пользователь " + loginInApp + "вышел из приложения ");
                break;
            default:
                System.out.println("Введите номер меню (Enter menu number)");
                break;
        }

    }

    protected void logInToTheAppCinema() throws ClassNotFoundException, IOException {

        GeneralController generalController = new GeneralController();
        System.out.println("Вход в приложение (Application Login)");
        System.out.println("Введите логин пользователя (Enter username)");
        String loginPersonRead = reader.readLine();
        if (personService.readLogin(loginPersonRead)) {
            System.out.println("Введите пароль пользователя (Enter user password)");
            String passwordPersonRead = reader.readLine();
            boolean toLogAndPasswordRegist = personService.readPasswordAddLog(loginPersonRead, passwordPersonRead);
            String personRole = personService.seachRole(loginPersonRead, passwordPersonRead);
            RoleClient roleClient = RoleClient.valueOf(personRole);
            if (toLogAndPasswordRegist) {
                switch (roleClient) {
                    case ADMIN: {
                        administratorController.menuAdministratorController(loginPersonRead);
                        break;
                    }
                    case MANAGER: {
                        managerController.menuManagerController(loginPersonRead);
                        break;
                    }
                    case CLIENT_PERSON: {
                        menuPersonController(loginPersonRead);
                        break;
                    }

                }
            }
            if (!toLogAndPasswordRegist) {
                logInToTheAppCinema();
            }
        } else {
            System.err.println("Данного логина не существует, пройдите регистрацию (This username does not exist, please register)");
            log.error("Данного логина не существует " + loginPersonRead + " " + date);
            generalController.menuCinema();
        }
    }

    public void registrationPersonInTheAppCinema() throws ClassNotFoundException, IOException {
        String loginPerson;
        String passwordPerson;
        int loginNumber;
        int passwordNumber;
        GeneralController generalController = new GeneralController();
        Person person = new Person();
        int numberLogin = 0;
        int numberPassword = 0;
        while (numberLogin < 3) {
            System.out.println("Введите логин пользователя (Enter username)");
            loginPerson = reader.readLine();
            loginNumber = loginPerson.length();
            if (loginNumber <= 5) {
                System.out.println("Слишком кароткий логин введите больше 5 символов/цифр");
            }
            if (loginPerson.isEmpty()) {
                System.out.println("Вы не ввели логин (You have not entered a login) " + (numberLogin + 1) + " раз (once)");
                log.error("Логин пользователя не введен, раз " + numberLogin + " " + date);
                numberLogin++;
            }
            if (!loginPerson.isEmpty()) {
                if (personService.readLogin(loginPerson)) {
                    System.out.println("Такой пользователь есть в базе (Such a user exists in the database)");

                }
                if (loginNumber >= 5) {
                    person.setLoginPerson(loginPerson);
                    System.out.println("Логин введен (Login entered)");
                    break;
                }
            }
            if (numberLogin == 3) {
                System.out.println("Колличество попыток закончилось пройдите регистрацию заново " +
                        "(The number of attempts has ended, please register again)");
                generalController.menuCinema();
            }
        }

        while (numberPassword < 3) {
            System.out.println("Введите пароль пользователя (Enter user password)");
            passwordPerson = reader.readLine();
            passwordNumber = passwordPerson.length();
            if (passwordNumber <= 5) {
                System.out.println("Слишком кароткий пароль введите больше 5 символов/цифр");
            }
            if (passwordPerson.isEmpty()) {
                System.err.println("Вы не ввели пароль (You have not entered a password )"
                        + (numberPassword + 1) + "раз (once)");
                log.error("Пароль пользователя " + person.getLoginPerson() + " введен не верно, раз "
                        + numberPassword + " " + date);
                numberPassword++;
            }
            if (numberPassword == 3) {
                System.err.println("Колличество попыток ввода пароля закончилось пройдите регистрацию заново " +
                        "(The number of password attempts has expired, please register again)");
                generalController.menuCinema();
            }
            if (!passwordPerson.isEmpty() && passwordNumber >= 5) {
                person.setPasswordPerson(passwordPerson);
                personService.create(person);
                System.out.println("Вы успешно зарегистрированы (You have successfully registered)");
                log.info("Успешная зарегистрация под логином " + person.getLoginPerson() + " " + date);
                generalController.menuCinema();
            }
        }
    }

}



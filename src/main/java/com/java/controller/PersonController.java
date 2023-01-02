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

    protected void menuPersonController(String loginInApp) {
        System.out.println("Вы вошли в приложение под логином: " + loginInApp);
        System.out.println("Дата входа " + date);
        System.out.println("1. Просмотреть доступные фильмы/мероприятия");
        System.out.println("2. Добавить фильмы/мероприятия");
        System.out.println("0. Выход");
        int number = scannerTwo.nextInt();
        switch (number) {
            case 1:
                filmService.viewEventsAndMovies(loginInApp);
                break;
            case 2:
                filmService.createEventsAndMovies();
                break;
            case 0:
                break;
            default:
                System.out.println("Введите номер меню");
                break;
        }

    }

    protected void logInToTheAppCinema() throws ClassNotFoundException {

        GeneralController generalController = new GeneralController();
        System.out.println("Вход в приложение");
        System.out.println("Введите логин пользователя");
        String loginPersonRead = scanner.nextLine();
                if (personService.readLogin(loginPersonRead)) {
            System.out.println("Введите пароль пользователя");
            String passwordPersonRead = scanner.nextLine();
            boolean toLogAndPasswordRegist = personService.readPasswordAddLog(loginPersonRead, passwordPersonRead);
            if (toLogAndPasswordRegist) {
                menuPersonController(loginPersonRead);
            }
            if (!toLogAndPasswordRegist) {
                logInToTheAppCinema();
            }
        } else {
            System.out.println("Данного логина не существует, пройдите регистрацию");
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
            System.out.println("Введите логин пользователя");
            loginPerson = scanner.nextLine();
            if (loginPerson.isEmpty()) {
                System.out.println("Вы не ввели логин " + (numberLogin + 1) + " раз");
                numberLogin++;
            }
            if (!loginPerson.isEmpty()) {
                if (personService.readLogin(loginPerson)) {
                    System.out.println("Такой пользователь есть в базе");

                } else {
                    person.setLoginPerson(loginPerson);
                    System.out.println("Логин введен");
                    break;
                }
            }
            if (numberLogin == 3) {
                System.out.println("Колличество попыток закончилось пройдите регистрацию заново");
                generalController.menuCinema();
            }
        }
        while (numberPassword < 3) {
            System.out.println("Введите пароль пользователя");
            passwordPerson = scanner.nextLine();
            if (passwordPerson.isEmpty()) {
                System.out.println("Вы не ввели пароль " + (numberPassword + 1) + " раз");
                numberPassword++;
            }
            if (!passwordPerson.isEmpty()) {
                person.setPasswordPerson(passwordPerson);
                personService.create(person);
                System.out.println("Вы успешно зарегистрированы");
                log.info("успешно зарегистрировался", person.getLoginPerson());
                generalController.menuCinema();
                scanner.remove();
                scannerTwo.remove();
            }
            if (numberPassword == 3) {
                System.out.println("Колличество попыток ввода пароля закончилось пройдите регистрацию заново");
                generalController.menuCinema();
            }
        }

    }

}

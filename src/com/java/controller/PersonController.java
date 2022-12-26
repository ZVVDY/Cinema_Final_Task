package com.java.controller;

import com.java.model.Person;
import com.java.service.PersonService;
import com.java.service.PersonServiceImp;

import java.util.Date;
import java.util.Scanner;

public class PersonController {
    private PersonService personService = new PersonServiceImp();
    private  Scanner scanner = new Scanner(System.in);
private Date date = new Date();

    protected void menuPersonController(String loginInApp) {
        System.out.println("Вы вошли в приложение под логином: " + loginInApp);
        System.out.println("Дата входа "+ date);
        System.out.println("1. Просмотреть доступные фильмы/мероприятия");
        System.out.println("2. Купить билет");
        System.out.println("3. Вернуть билет");
        System.out.println("4. Назад");
        System.out.println("5. Выход");
        int number=scanner.nextInt();
        switch (number) {
            case 1:
                //logInClient();
                break;
            case 2:
                //handleClientRegister();
                break;
            case 3:
                // handleBuyTicket(0);
            case 4:


                //menu = Menu.MAIN;
                break;
            case 5:
                // menu = Menu.EXIT;
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
        }
        else
        {
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
                    System.out.println("Вы не ввели пароль " + (numberPassword+1) + " раз");
                    numberPassword++;
                }
                if (!passwordPerson.isEmpty()) {
                    person.setPasswordPerson(passwordPerson);
                    personService.create(person);
                    System.out.println("Вы успешно зарегистрированы");
                    generalController.menuCinema();
                }
                if (numberPassword== 3) {
                    System.out.println("Колличество попыток ввода пароля закончилось пройдите регистрацию заново");
                    generalController.menuCinema();
                }
            }

        }

    }

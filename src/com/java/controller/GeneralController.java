package com.java.controller;

import com.java.model.*;
import com.java.model.RoleClient;
import com.java.repository.PersonRepository;
import com.java.repository.PersonRepositoryImp;
import com.java.service.PersonService;
import com.java.service.PersonServiceImp;

import java.util.Scanner;

public class GeneralController {
    private static Scanner scanner = new Scanner(System.in);
    private static Scanner scannerTwo = new Scanner(System.in);
    private PersonService service = new PersonServiceImp();
    private PersonRepository personRepository = new PersonRepositoryImp();
    private RoleClient roleClient;
    private String loginPerson;
    private String passwordPerson;
    private PersonController personController = new PersonController();
    public void menuCinema() throws ClassNotFoundException {
        Integer number;
                System.out.println("Добро пожаловать в КИНОТЕАТР!");
        System.out.println("Введите" + "\n" + "1 - войти в приложение" + "\n" +
                "2 - зарегистрироваться в приложении" + "\n" + "0 - выйти из приложения");
        number = scanner.nextInt();
        if (number == 1) {
            personController.logInToTheAppCinema();
        }
        if (number == 2) {
            Person person = new Person();
            int numberLogin = 0;
            int numberPassword = 0;
            while (numberLogin < 3) {
                System.out.println("Введите логин пользователя");
                loginPerson = scannerTwo.nextLine();
                if (loginPerson.isEmpty()) {
                    System.out.println("Вы не ввели логин " + (numberLogin + 1) + " раз");
                    numberLogin++;
                }
                if (!loginPerson.isEmpty()) {
                    if (personRepository.searchForARegisteredPerson(loginPerson)) {
                        System.out.println("Такой пользователь есть в базе");

                    } else {
                        person.setLoginPerson(loginPerson);
                        System.out.println("Логин введен");
                        break;
                    }
                }
                if (numberLogin == 3) {
                    System.out.println("Колличество попыток закончилось пройдите регистрацию заново");
                    menuCinema();
                }
            }
            while (numberPassword < 3) {
                System.out.println("Введите пароль пользователя");
                passwordPerson = scannerTwo.nextLine();
                if (passwordPerson.isEmpty()) {
                    System.out.println("Вы не ввели пароль " + (numberPassword+1) + " раз");
                    numberPassword++;
                }
                if (!passwordPerson.isEmpty()) {
                    person.setPasswordPerson(passwordPerson);
                    service.create(person);
                    System.out.println("Вы успешно зарегистрированы");
                    menuCinema();
                }
                if (numberPassword== 3) {
                    System.out.println("Колличество попыток ввода пароля закончилось пройдите регистрацию заново");
                    menuCinema();
                }
            }
        }
        if (number == 0) {
            System.out.println("Вы вышли из программы кинотеатр");
        }
    }
}

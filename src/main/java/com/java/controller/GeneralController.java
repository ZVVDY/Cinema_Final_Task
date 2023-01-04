package com.java.controller;

import com.java.repository.PersonRepository;
import com.java.repository.PersonRepositoryImp;
import com.java.service.PersonService;
import com.java.service.PersonServiceImp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GeneralController {

    private PersonService service = new PersonServiceImp();
    private PersonRepository personRepository = new PersonRepositoryImp();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Integer number;
    private PersonController personController = new PersonController();

    public void menuCinema() throws ClassNotFoundException {

        System.out.println("Добро пожаловать в КИНОТЕАТР!(Welcome to CINEMA!)");
        System.out.println("Войти (Enter)" + "\n" + "1 - Войдите в приложение(Enter the app)" + "\n" +
                "2 - Зарегистрируйтесь в приложении(Register in the app)" + "\n" + "0 - Выйти из приложения(Exit the application)");
        try {
            number = Integer.parseInt(reader.readLine());
            switch (number) {
                case 1:
                    personController.logInToTheAppCinema();
                    break;
                case 2:
                    personController.registrationPersonInTheAppCinema();
                    break;
                case 0:
                    System.out.println("Вы вышли из программы Кинотеатр (You are out of the Cinema program)");
                    break;
                default:
                    System.out.println("Введите номер меню 0,1,2 (Enter menu number 0,1,2)");
                    menuCinema();
            }
        } catch (Exception e) {
            System.err.println("Введите цифры 0,1,2, а не символы(Enter the number 0,1,2, not symbols)");
        } finally {
            menuCinema();
        }
    }
}


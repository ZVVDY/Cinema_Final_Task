package com.java.controller;

import com.java.model.RoleClient;
import com.java.repository.PersonRepository;
import com.java.repository.PersonRepositoryImp;
import com.java.service.PersonService;
import com.java.service.PersonServiceImp;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GeneralController {
    private  Scanner scanner = new Scanner(System.in);
    private  Scanner scannerTwo = new Scanner(System.in);
    private PersonService service = new PersonServiceImp();
    private PersonRepository personRepository = new PersonRepositoryImp();
    private RoleClient roleClient;

    private PersonController personController = new PersonController();

    public void menuCinema() throws ClassNotFoundException {
        Integer number;
        System.out.println("Добро пожаловать в КИНОТЕАТР!");
        System.out.println("Введите" + "\n" + "1 - войти в приложение" + "\n" +
                "2 - зарегистрироваться в приложении" + "\n" + "0 - выйти из приложения");

        try {
            number = scanner.nextInt();
            switch (number) {
                case 1:
                    personController.logInToTheAppCinema();
                case 2:
                    personController.registrationPersonInTheAppCinema();
                case 0:
                    System.out.println("Вы вышли из программы Кинотеатр");
                    break;
                default:
                    System.out.println("Введите номер меню");
                    menuCinema();
            }
        } catch (InputMismatchException e) {
            System.err.println("Введите число 0,1,2, а не символы");
        }
    }
}


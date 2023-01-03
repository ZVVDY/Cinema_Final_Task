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


    private PersonController personController = new PersonController();

    public void menuCinema() throws ClassNotFoundException {
        Integer number;
        System.out.println("Добро пожаловать в КИНОТЕАТР!(Welcome to CINEMA!)");
        System.out.println("Войти (Enter)" + "\n" + "1 - Войдите в приложение(Enter the app)" + "\n" +
                "2 - Зарегистрируйтесь в приложении(Register in the app)" + "\n" + "0 - Выйти из приложения(Exit the application)");
        try {
            number = scanner.nextInt();
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
                    System.out.println("Введите номер меню (Enter menu number)");
                    menuCinema();
            }
        } catch (InputMismatchException e) {
            System.err.println("Введите цифры 0,1,2, а не символы(Enter the number 0,1,2, not symbols)");
        }
    }
}


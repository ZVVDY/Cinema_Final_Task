package com.java.controller;

import java.util.Scanner;
import com.java.model.*;
import com.java.service.*;
import com.java.repository.*;
public class PersonController {
    PersonService personService = new PersonServiceImp();
private static Scanner scanner = new Scanner(System.in);
//    void menuPersonController(){
//        System.out.println("1. Просмотреть доступные фильмы/мероприятия");
//        System.out.println("2. Купить билет");
//        System.out.println("3. Вернуть билет");
//        System.out.println("4. Назад");
//        System.out.println("5. Выход");
//
//        switch (scanner.nextInt()) {
//            case 1:
//                logInClient();
//                break;
//            case 2:
//                handleClientRegister();
//                break;
//            case 3:
//                handleBuyTicket(0);
//            case 4:
//
//
//                menu = Menu.MAIN;
//                break;
//            case 5:
//                                                menu = Menu.EXIT;
//                break;
//            default:
//                System.out.println("ВВедите номер меню");
//                break;
//        }
//
//    }
    public void logInToTheAppCinema() {
        Person personRead = new Person();
        System.out.println("Вход в приложение");
        System.out.println("Введите логин пользователя");
        String loginPersonRead = scanner.nextLine();
        System.out.println("Введите пароль пользователя");
        String passwordPersonRead = scanner.nextLine();
        personRead.setLoginPerson(loginPersonRead);
        personRead.setLoginPerson(passwordPersonRead);
        boolean logInTheApp = personService.read(personRead);

    }

    public void registrationPersonInTheAppCinema() throws ClassNotFoundException {


    }
}
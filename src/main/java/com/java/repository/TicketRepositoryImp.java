package com.java.repository;

import com.java.controller.PersonController;
import com.java.model.Ticket;
import com.java.service.PersonService;
import com.java.service.PersonServiceImp;

import java.sql.*;
import java.util.Scanner;

public class TicketRepositoryImp implements TicketRepository {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3307/cinema";
    private static String username = "root";
    private static String password = "";
    private Connection connect;
    private PreparedStatement ps;
    private String sql;
    private Scanner scanner = new Scanner(System.in);
    private PersonService personService = new PersonServiceImp();

    @Override
    public void buyAMovieTicketPerson(String loginApp) {
        Ticket ticket = new Ticket();
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список доступных билетов для покупки");
            sql = "SELECT * FROM `ticket`";
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println("1.Фильм " + resultSet.getString("filmname") + " Номер места: " +
                        resultSet.getString("numberplace") + " Стоимость билета: " +
                        resultSet.getString("costtiket") + " Место свободно " + resultSet.getString("flagTicketPurchased"));
            }
            System.out.println("Введите номер места для покупки билета");
            Integer scannerBayTiketPlace = scanner.nextInt();
            ticket.setIdTicket(1L);
            ticket.setPersonTicket(loginApp);
            ticket.setNumberPlacel(scannerBayTiketPlace);
            ticket.setFlagTicketPurchased(true);

            sql = String.format(("UPDATE `ticket` SET `username`='%s', `flagTicketPurchased` =%b WHERE `numberplace`=%d"), loginApp, ticket.getFlagTicketPurchased(), scannerBayTiketPlace);

            ps = connect.prepareStatement(sql);

            boolean a = ps.execute();

//            while (a=false) {
//                System.out.println("Билет куплен, номер места " + ticket.getNumberPlacel());
//
//            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void refundMovieTicketPerson(String personName) {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список купленных билетов");
            sql = String.format("SELECT * FROM `ticket` WHERE `username`='%s'", personName);
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            //if (resultSet.next()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("id_film") + " Фильм " + resultSet.getString("filmname") + " Номер места: " +
                            resultSet.getString("numberplace") + " Стоимость билета: " +
                            resultSet.getString("costtiket") + " Место свободно " + resultSet.getString("flagTicketPurchased"));
                }
                System.out.println("Введите номер места для возврата билета");
                Integer scannerBayTiketPlace = scanner.nextInt();
//            ticket.setIdTicket(1L);
//            ticket.setPersonTicket(loginApp);
//            ticket.setNumberPlacel(scannerBayTiketPlace);
                //ticket.setFlagTicketPurchased(true);

                sql = String.format(("UPDATE `ticket` SET `username`=NULL, `flagTicketPurchased` =0 WHERE `numberplace`=%d"), scannerBayTiketPlace);

                ps = connect.prepareStatement(sql);

                boolean a = ps.execute();
                System.out.println("Возрат билета выполнен");
               // refundMovieTicketPerson(personName);

            if (!resultSet.next()) {
                System.out.println("У вас нет купленных билетов");
                System.out.println("Введите 0 ,что бы вернуться в предыдущее меню");
                int numberBack = scanner.nextInt();
                if (numberBack == 0) {
                    PersonController personController = new PersonController();
personController.menuPersonController(personName);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public void viewPurchasedMovieTicketsPerson () {

        }
    }

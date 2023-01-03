package com.java.repository;

import com.java.controller.PersonController;
import com.java.model.RoleClient;
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
            System.out.println("Список доступных билетов для покупки (List of available tickets for purchase)");
            sql = "SELECT * FROM `ticket` WHERE `flagTicketPurchased`=0";
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id_film") + " Film " + resultSet.getString("filmname") + " Place number: " +
                        resultSet.getString("numberplace") + " Ticket price: " +
                        resultSet.getString("costtiket") + " Место свободно " + resultSet.getString("flagTicketPurchased").equals("0"));
            }
            System.out.println("Введите номер места для покупки билета (Enter seat number for ticket purchase)");
            Integer scannerBayTiketPlace = scanner.nextInt();
//            ticket.setIdTicket(1L);
//            ticket.setPersonTicket(loginApp);
//            ticket.setNumberPlacel(scannerBayTiketPlace);
            ticket.setFlagTicketPurchased(true);

            sql = String.format(("UPDATE `ticket` SET `username`='%s', `flagTicketPurchased` =%b WHERE `numberplace`=%d"), loginApp, ticket.getFlagTicketPurchased(), scannerBayTiketPlace);

            ps = connect.prepareStatement(sql);

            ps.execute();

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
            System.out.println("Список купленных билетов (List of purchased tickets)");
            sql = String.format("SELECT * FROM `ticket` WHERE `username`='%s'", personName);
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            //if (resultSet.next()) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id_film") + " Film " + resultSet.getString("filmname") + " Place number: " +
                        resultSet.getString("numberplace") + " Ticket price: " +
                        resultSet.getString("costtiket") + " Place is free " + resultSet.getString("flagTicketPurchased").equals("0"));
            }
            System.out.println("Введите номер места для возврата билета( Enter seat number for ticket refund)");//
            Integer scannerBayTiketPlace = scanner.nextInt();
//            ticket.setIdTicket(1L);
//            ticket.setPersonTicket(loginApp);
//            ticket.setNumberPlacel(scannerBayTiketPlace);
            //ticket.setFlagTicketPurchased(true);

            sql = String.format(("UPDATE `ticket` SET `username`=NULL, `flagTicketPurchased` =0 WHERE `numberplace`=%d"), scannerBayTiketPlace);

            ps = connect.prepareStatement(sql);

            ps.execute();
            System.out.println("Возрат билета выполнен (Ticket refund completed)");
            // refundMovieTicketPerson(personName);

            if (!resultSet.next()) {
                System.out.println(" У вас нет купленных билетов (You have no purchased tickets)");
                System.out.println("Введите 0 ,что бы вернуться в предыдущее меню (Enter 0 to return to the previous menu)");
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
    public void viewPurchasedMovieTicketsPerson(String nameLogin) {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список купленных билетов (List of purchased tickets)");
            sql = String.format("SELECT * FROM `ticket` WHERE `username`='%s'", nameLogin);
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                System.out.println("У вас нет купленных билетов (You do not have purchased tickets)");
                System.out.println("Выберете мероприятия для покупки (Select events to buy)");
                PersonController personController = new PersonController();
                personController.menuPersonController(nameLogin);
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id_film") + " Film " + resultSet.getString("filmname") + " Place number: " +
                        resultSet.getString("numberplace") + " Ticket price: " +
                        resultSet.getString("costtiket") + " Place is free " + resultSet.getString("flagTicketPurchased").equals("0"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createTicket(int id, String nameFilm, int numberTicket) {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            int s = 1;
            while (s < numberTicket) {
                sql = "INSERT INTO ticket (id_film, filmname, numberplace) VALUES (?,?,?)";
                ps = connect.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2, nameFilm);
                ps.setInt(3, numberTicket);
                ps.execute();
                s++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection MySQL");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

return true;
    }
}

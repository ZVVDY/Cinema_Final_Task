package com.java.repository;

import com.java.model.Ticket;

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

// TODO: 29.12.2022


            sql = String.format(("UPDATE `ticket` SET `username`='%s', `flagTicketPurchased` =%b WHERE `numberplace`=%d"),loginApp,ticket.getFlagTicketPurchased(),scannerBayTiketPlace);
            //sql="INSERT INTO ticket (id, username, numberplace,flagTicketPurchased ) VALUES (?,?,?,?)";
            //UPDATE `ticket` SET `username`='%s', 'flagTicketPurchased' ='' WHERE `numberplace`='%d'
           // UPDATE `ticket` SET `username`='%s', `flagTicketPurchased` =%b WHERE `numberplace`=%d;
            ps = connect.prepareStatement(sql);
//         ps.setLong(1, ticket.getIdTicket());
//            ps.setString(2, ticket.getPersonTicket());
//            ps.setInt(3, ticket.getNumberPlacel());
//           ps.setBoolean(4, ticket.getFlagTicketPurchased());
            //ps.execute();
boolean a = ps.execute();

while (a) {
    System.out.println("Билет куплен, номер места " + ticket.getNumberPlacel());

}
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void refundMovieTicketPerson() {

    }

    @Override
    public void viewPurchasedMovieTicketsPerson() {

    }
}

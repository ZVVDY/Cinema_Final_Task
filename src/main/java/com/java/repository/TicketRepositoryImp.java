package com.java.repository;

import com.java.controller.PersonController;
import com.java.model.Ticket;
import com.java.service.FilmService;
import com.java.service.FilmServiceImp;
import com.java.service.PersonService;
import com.java.service.PersonServiceImp;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

@Slf4j
public class TicketRepositoryImp implements TicketRepository {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3307/cinema";
    private static String username = "root";
    private static String password = "";
    private Connection connect;
    private PreparedStatement ps;
    private String sql;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private PersonService personService = new PersonServiceImp();


    @Override
    public void buyAMovieTicketPerson(String loginApp, int idFilm) {
        log.info("Пользователь " + loginApp + " вошел в меню покупки билетов ");
        Ticket ticket = new Ticket();
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список доступных билетов для покупки (List of available tickets for purchase)");
            sql = String.format(("SELECT * FROM `ticket` WHERE `flagTicketPurchased`=0 AND `id_film` = %d"), idFilm);
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id_film") + " Film " + resultSet.getString("filmname") + " Place number: " +
                        resultSet.getString("numberplace") + " Ticket price: " +
                        resultSet.getString("cost_ticket") + " Place is free " + resultSet.getString("flagTicketPurchased").equals("0"));
            }
            System.out.println("Введите номер места для покупки билета (Enter seat number for ticket purchase)");
            Integer scannerBayTiketPlace = Integer.parseInt(reader.readLine());
            log.info("Выбрано место " + scannerBayTiketPlace);
            ticket.setFlagTicketPurchased(true);
            sql = String.format(("UPDATE `ticket` SET `username`='%s', `flagTicketPurchased` =%b WHERE " +
                            "`numberplace`=%d AND `id_film` = %d"), loginApp, ticket.getFlagTicketPurchased(), scannerBayTiketPlace,
                    idFilm);
            ps = connect.prepareStatement(sql);
            ps.execute();
            log.info("Пользователь совершил покупку на мероприятие/фильм ");
        } catch (SQLException | ClassNotFoundException | IOException e) {
            log.error("Ошибка в покупке билета");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void buyAMovieTicketPerson() {
        log.info("Пользователь открыл  меню покупки билетов на мероприятие/фильм ");
        FilmService filmService = new FilmServiceImp();
        String namePerson = personService.searchForAPersonInTheDatabase();
        filmService.viewEventsAndMovies(namePerson);

    }

    @Override
    public void refundMovieTicketPerson(String personName) {
        log.info("Пользователь " + personName + " открыл меню возврата билета ");
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список купленных билетов (List of purchased tickets)");
            sql = String.format("SELECT * FROM `ticket` WHERE `username`='%s'", personName);
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id_film") + " Film " + resultSet.getString("filmname") + " Place number: " +
                        resultSet.getString("numberplace") + " Ticket price: " +
                        resultSet.getString("cost_ticket") + " Place is free " + resultSet.getString("flagTicketPurchased").equals("0"));
            }
            System.out.println("Введите id фильма для возврата билета( Enter ID film number for ticket refund)");
            Integer idFilm = Integer.parseInt(reader.readLine());
            System.out.println("Введите номер места для возврата билета( Enter seat number for ticket refund)");
            Integer scannerBayTiketPlace = Integer.parseInt(reader.readLine());
            sql = String.format(("UPDATE ticket SET username=NULL, flagTicketPurchased =0 WHERE numberplace=%d AND " +
                    "id_film = %d AND username ='%s'"), scannerBayTiketPlace, idFilm, personName);
            ps = connect.prepareStatement(sql);
            ps.execute();
            System.out.println("Возрат билета выполнен (Ticket refund completed)");
            log.info("Пользователь " + personName + " вернул билет" + scannerBayTiketPlace + " на фильм/мероприятие ");
            if (!resultSet.next()) {
                System.out.println(" У вас нет купленных билетов (You have no purchased tickets)");
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void viewPurchasedMovieTicketsPerson(String nameLogin) {
        log.info("Пользователь " + nameLogin + " открыл список купленных фильмов мероприятий для просмотра");
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список купленных билетов фильмов мероприятий для просмотра (List of events films to watch)");
            sql = String.format("SELECT * FROM `ticket` WHERE `username`='%s'", nameLogin);
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                System.out.println("У вас нет купленных билетов (You do not have purchased tickets)");
                System.out.println("Выберете мероприятия для покупки (Select events to buy)");
            } else {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("id_film") + " Film/Event " + resultSet.getString("filmname") + " Place number: " +
                            resultSet.getString("numberplace") + " Ticket price: " +
                            resultSet.getString("cost_ticket") + " Place is free " + resultSet.getString("flagTicketPurchased").equals("0"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTicket() {
        log.info("Пользователь открыл  меню создания билетов");
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            sql = "SELECT id, namemovie, dateandtimefilm, quantityticket,cost_ticket FROM film";
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " Film/Event " + resultSet.getString("namemovie") + " Date film: " + resultSet.getString("dateandtimefilm") + " Ticket price: " +
                        resultSet.getString("quantityticket") + " Cost one ticket:" + resultSet.getString("cost_ticket"));
            }
            System.out.println("Введите id фильма для добавления билетов (Enter movie id to add tickets)");
            int scanId = Integer.parseInt(reader.readLine());
            sql = String.format("SELECT id, namemovie, dateandtimefilm, quantityticket, cost_ticket FROM film WHERE id = %d", scanId);
            ps = connect.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int numberTicketCopyInBase = 1;
                int id = Integer.parseInt(resultSet.getString("id"));
                String nameFilm = resultSet.getString("namemovie");
                int numberPlace = Integer.parseInt(resultSet.getString("quantityticket"));
                int costTicket = Integer.parseInt(resultSet.getString("cost_ticket"));
                while (numberTicketCopyInBase <= numberPlace) {
                    sql = "INSERT INTO ticket (id_film, filmname, numberplace, cost_ticket) VALUES (?,?,?,?)";
                    ps = connect.prepareStatement(sql);
                    ps.setInt(1, id);
                    ps.setString(2, nameFilm);
                    ps.setInt(3, numberTicketCopyInBase);
                    ps.setInt(4, costTicket);
                    ps.execute();
                    numberTicketCopyInBase++;
                }
                log.info("Создано " + numberPlace + " на мероприятия/фильм" + nameFilm);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection MySQL");
            log.error(" Ошибка подключения к базе данных");

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
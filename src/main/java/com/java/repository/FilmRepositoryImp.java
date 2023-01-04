package com.java.repository;

import com.java.model.Film;
import com.java.service.TicketService;
import com.java.service.TicketServiceImp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class FilmRepositoryImp implements FilmRepository {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3307/cinema";
    private static String username = "root";
    private static String password = "";
    private Connection connect;
    private PreparedStatement ps;
    private String sql;
    private Scanner scanner = new Scanner(System.in);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private TicketRepository ticketRepository = new TicketRepositoryImp();
    private TicketService service = new TicketServiceImp();

    @Override
    public void viewEventsAndMovies(String loginApp) {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            sql = "SELECT * FROM `film`";
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " Film/Event " + resultSet.getString("namemovie") + " Session start: " +
                        resultSet.getString("dateandtimefilm") + " Number of available places: " +
                        resultSet.getInt("quantityticket"));
            }
            System.out.println("Введите номер фильма для покупки билета(Enter movie number to buy ticket)");
            switch (scanner.nextInt()) {
                case 1:
                    service.buyAMovieTicket(loginApp);

                    break;
                case 0:
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void editEventsAndMovies() {
        Film film = new Film();
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список  фильмов/мероприятий для редактирования (List list of movie events to edit)");
            sql = "SELECT * FROM `film`";
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " Film/Event " + resultSet.getString("namemovie") +
                        " Date and time film/event: " +
                        resultSet.getString("dateandtimefilm") + " Quantity ticket: " +
                        resultSet.getString("quantityticket") + " Cost one ticket " + resultSet.getString("cost_ticket"));
            }

            System.out.println("Введите id фильма/мероприятия  для редактирования (Enter event movie id to edit)");
            int scanId = scanner.nextInt();
            sql = String.format("SELECT id, namemovie, dateandtimefilm, quantityticket, cost_ticket FROM film WHERE id = %d", scanId);
            ps = connect.prepareStatement(sql);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                System.out.println("Введите новое название мероприятия (Enter a new name for the event)");
                film.setNameMovie(reader.readLine());
                System.out.println("Введите  дату и время  мероприятия /фильм в формате хххх-хх-хх хх:хх:хх( например 2023-01-07 16:00:00)" +
                        "(Enter the date and time of the event movie in the format xxxx-xx-xx xx:xx:xx(for example 2023-01-07 16:00:00)");
                String dateInString = reader.readLine();
                film.setDateAndTimeFilm(dateInString);
                System.out.println("Введите  новое количество мест (максимум 50), мероприятия /фильм (Enter the new number of seats (maximum 50), events movie)");
                film.setQuantityTicket(scanner.nextInt());
                System.out.println("Введите  новую стоимость билета на мероприятия /фильм (Enter the new ticket price for events movie)");
                film.setCostTicket(scanner.nextInt());
                sql = String.format(("UPDATE `film` SET `namemovie`='%s', `dateandtimefilm` ='%s',`quantityticket`=%d,`cost_ticket`=%d WHERE `id`=%d"), film.getNameMovie(), film.getDateAndTimeFilm(), film.getQuantityTicket(),film.getCostTicket(),scanId);
                ps = connect.prepareStatement(sql);
                ps.execute();
                System.out.println("Изменения внесены успешно(Changes made successfully)");
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void createEventsAndMovies() {
        BufferedReader readerTwo = new BufferedReader(new InputStreamReader(System.in));
        Film film = new Film();
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Введите  название мероприятия /фильм(Enter the name of the event movie)");
            film.setNameMovie(reader.readLine());
            System.out.println("Введите  дату и время  мероприятия /фильм в формате хххх-хх-хх хх:хх:хх( например 2023-01-07 16:00:00)" +
                    "(Enter the date and time of the event movie in the format xxxx-xx-xx xx:xx:xx(for example 2023-01-07 16:00:00)");
            String dateInString = readerTwo.readLine();
            film.setDateAndTimeFilm(dateInString);
            System.out.println("Введите  количество мест (максимум 50), мероприятия /фильм (Enter the number of seats (maximum 50), events movie)");
            film.setQuantityTicket(scanner.nextInt());
            System.out.println("Введите  стоимость билета на мероприятия /фильм (Enter the ticket price for events movie)");
            film.setCostTicket(scanner.nextInt());
            sql = String.format("INSERT INTO film(namemovie, dateandtimefilm, quantityticket,cost_ticket) VALUES ('%s','%s',%d,%d)", film.getNameMovie(), film.getDateAndTimeFilm(),
                    film.getQuantityTicket(), film.getCostTicket());
            ps = connect.prepareStatement(sql);
            ps.execute();
//TODO хочу создавать билеты, добавить метод
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection MySQL");

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteEventsAndMovies() {

    }
}

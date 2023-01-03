package com.java.repository;

import com.java.model.Film;
import com.java.service.TicketService;
import com.java.service.TicketServiceImp;
import org.apache.commons.lang3.time.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;
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
                System.out.println(resultSet.getString("id") + " Film " + resultSet.getString("namemovie") + " Session start: " +
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


    }

    @Override
    public void createEventsAndMovies() {
        BufferedReader readerTwo = new BufferedReader(new InputStreamReader(System.in));
        Film film = new Film();
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Введите  название мероприятия /фильм");
            film.setNameMovie(reader.readLine());
            System.out.println("Введите  дату и время  мероприятия /фильм в формате хххх-хх-хх хх:хх:хх( например 2023-01-07 16:00:00)");
            String dateInString = readerTwo.readLine();
            film.setDateAndTimeFilm(dateInString);
            System.out.println("Введите  количество мест (максимум 50), мероприятия /фильм");
            film.setQuantityTicket(scanner.nextInt());
            sql = String.format("INSERT INTO film(namemovie, dateandtimefilm, quantityticket) VALUES ('%s','%s',%d)", film.getNameMovie(), film.getDateAndTimeFilm(), film.getQuantityTicket());
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

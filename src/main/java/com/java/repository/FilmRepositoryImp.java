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
                System.out.println(resultSet.getString("id") + " Фильм " + resultSet.getString("namemovie") + " Начало сеанса: " +
                        resultSet.getString("dateandtimefilm") + " Количество свободных мест: " +
                        resultSet.getInt("quantityticket"));
            }
                System.out.println("Введите номер фильма для покупки билета");
                switch (scanner.nextInt()) {
                    case 1:
                        service.buyAMovieTicket(loginApp);
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
//            System.out.println("Введите №id мероприятие /фильм");
//            film.setIdMovie(scanner.nextInt());
            System.out.println("Введите  название мероприятия /фильм");
            film.setNameMovie(readerTwo.readLine());
            System.out.println("Введите  дату и время  мероприятия /фильм в формате хххх-хх-хх хх:хх:хх( например 2023-01-07 16:00:00)");
            film.setDateAndTimeFilm(readerTwo.readLine());
            System.out.println("Введите  количество мест (максимум 50), мероприятия /фильм");
            film.setQuantityTicket(readerTwo.read());
            //sql = "INSERT INTO film (id, namemovie, dateandtimefilm, quantityticket) VALUES (?,?,?,?)";
                    //sql = String.format("INSERT INTO 'film' 'id'='%s', 'namemovie'='%s', 'dateandtimefilm'='%s', 'quantityticket'='%s'" , film.getIdMovie(),film.getNameMovie(),film.getDateAndTimeFilm(),film.getQuantityTicket() );
                    //sql="INSERT INTO `film`(`id`, `namemovie`, `dateandtimefilm`, `quantityticket`) VALUES ('film.getIdMovie()','film.getNameMovie()','getDateAndTimeFilm()','getQuantityTicket()')";
            sql= "INSERT INTO `film`(`namemovie`, `dateandtimefilm`, `quantityticket`) VALUES ('?','?','?')";
                    ps = connect.prepareStatement(sql);
            ps.setString(1, film.getNameMovie());
            ps.setString(2, film.getDateAndTimeFilm());
            ps.setInt(3, film.getQuantityTicket());
            ps.execute();


            System.out.println("Мероприятие создано"+ps.execute());
                }

catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection MySQL");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
                    throw new RuntimeException(e);
                }
    }


        @Override
    public void deleteEventsAndMovies() {

    }
}

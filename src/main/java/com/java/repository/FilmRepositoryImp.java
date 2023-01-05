package com.java.repository;

import com.java.model.Film;
import com.java.service.TicketService;
import com.java.service.TicketServiceImp;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Date;

@Slf4j
public class FilmRepositoryImp implements FilmRepository {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3307/cinema";
    private static String username = "root";
    private static String password = "";
    private Connection connect;
    private PreparedStatement ps;
    private String sql;

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public TicketService ticketService = new TicketServiceImp();
    private Date date = new Date();

    @Override
    public void viewEventsAndMovies(String loginApp) {
        log.info("Пользователь " + loginApp + " вошел меню просмотр мероприятий и фильмов  " + date);
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
            int idFilm = Integer.parseInt(reader.readLine());
            if (idFilm != 0) {
                ticketService.buyAMovieTicket(loginApp, idFilm);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void editEventsAndMovies() {
        log.info("Пользователь вошел меню редактирования мероприятий и фильмов  ");
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
            int scanId = Integer.parseInt(reader.readLine());
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
                film.setQuantityTicket(Integer.parseInt(reader.readLine()));
                System.out.println("Введите  новую стоимость билета на мероприятия /фильм (Enter the new ticket price for events movie)");
                film.setCostTicket(Integer.parseInt(reader.readLine()));
                sql = String.format(("UPDATE `film` SET `namemovie`='%s', `dateandtimefilm` ='%s',`quantityticket`=%d,`cost_ticket`=%d WHERE `id`=%d"), film.getNameMovie(), film.getDateAndTimeFilm(), film.getQuantityTicket(), film.getCostTicket(), scanId);
                ps = connect.prepareStatement(sql);
                ps.execute();
                System.out.println("Изменения внесены успешно(Changes made successfully)");
                log.info("Пользователь отредактировал мероприятие/фильм  " + film.getNameMovie());
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createEventsAndMovies() {
        log.info("Пользователь вошел меню создания мероприятий и фильмов");
        Film film = new Film();
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Введите  название мероприятия /фильм(Enter the name of the event movie)");
            film.setNameMovie(reader.readLine());
            System.out.println("Введите  дату и время  мероприятия /фильм в формате хххх-хх-хх хх:хх:хх( например 2023-01-07 16:00:00)" +
                    "(Enter the date and time of the event movie in the format xxxx-xx-xx xx:xx:xx(for example 2023-01-07 16:00:00)");
            String dateInString = reader.readLine();
            film.setDateAndTimeFilm(dateInString);
            System.out.println("Введите  количество мест (максимум 50), мероприятия /фильм (Enter the number of seats (maximum 50), events movie)");
            film.setQuantityTicket(Integer.parseInt(reader.readLine()));
            System.out.println("Введите  стоимость билета на мероприятия /фильм (Enter the ticket price for events movie)");
            film.setCostTicket(Integer.parseInt(reader.readLine()));
            sql = String.format("INSERT INTO film(namemovie, dateandtimefilm, quantityticket,cost_ticket) VALUES ('%s','%s',%d,%d)", film.getNameMovie(), film.getDateAndTimeFilm(),
                    film.getQuantityTicket(), film.getCostTicket());
            ps = connect.prepareStatement(sql);
            ps.execute();
            log.info("Мероприятие/фильм создано " + film.getNameMovie());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection MySQL");

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEventsAndMovies() {
        log.info("Пользователь вошел меню удаления мероприятий и фильмов  ");
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список фильмов/мероприятий (List of film events)");
            sql = "SELECT * FROM `film` ";
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println("id" + resultSet.getString("id") + " namemovie " + resultSet.getString("namemovie"));
            }
            System.out.println("Введите id фильма/мероприятия (Enter event movie id)");
            int idFilm = Integer.parseInt(reader.readLine());
            System.out.println("Введите название фильма/мероприятия(Enter the namemovie movieevent)");
            String nameFilm = reader.readLine();
            sql = String.format("SELECT EXISTS (SELECT * FROM film WHERE id = %d AND namemovie = '%s')",
                    idFilm, nameFilm);
            ps = connect.prepareStatement(sql);
            resultSet = ps.executeQuery(sql);
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count == 1) {
                    sql = String.format("DELETE FROM `film` WHERE `id`= %d ", idFilm);
                    ps = connect.prepareStatement(sql);
                    ps.executeUpdate();
                    System.out.println("Удаление фильма/мероприятия(Delete movie event) " + nameFilm +
                            " выполнено (performed)");
                    log.info("Удалениея мероприятий/фильмов  " + nameFilm + " выполнено");
                    System.out.println("Происходит очистка билетов фильма/мероприятия " +
                            "(Tickets occupied by the user are cleared)" + nameFilm);
                    sql = String.format(("DELETE FROM `ticket` WHERE `id_film`= %d "), idFilm);
                    ps = connect.prepareStatement(sql);
                    ps.execute();
                    System.out.println("Удаление билетов и базы  выполнено (Deletion of tickets and database completed)");
                    log.info("Удалениея билетов мероприятий/фильмов  " + nameFilm + " выполнено");
                }
                if (count == 0) {
                    System.err.println("Ошибка удаления мероприятия/фильма(Error deleting film/event) " + nameFilm);
                    log.error("Ошибка удаления мероприятия/фильма(Error deleting film/event) " + nameFilm);
                }
            }
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void watchMoviesEventsAtTheCinema(String loginApp) {
        log.info("Пользователь " + loginApp + " вошел меню просмотра мероприятий и фильмов  " + date);
        System.out.println("Фильмы/мероприятия доступные для просмотра в Кинотеатре" +
                "(Movies Events available for viewing at the Cinema)");
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
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


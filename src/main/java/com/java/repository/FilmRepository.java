package com.java.repository;

public interface FilmRepository {
    void viewEventsAndMovies(String loginApp);

    void editEventsAndMovies();

    void createEventsAndMovies();

    void deleteEventsAndMovies();

    void watchMoviesEventsAtTheCinema(String loginApp);
}

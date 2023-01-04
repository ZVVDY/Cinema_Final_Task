package com.java.repository;

public interface TicketRepository {
    void buyAMovieTicketPerson(String loginApp, int idFilm);

    void buyAMovieTicketPerson();

    void refundMovieTicketPerson(String namePerson);

    void viewPurchasedMovieTicketsPerson(String nameLogin);

    void createTicket();
}

package com.java.repository;

public interface TicketRepository {
    void buyAMovieTicketPerson(String loginApp);
    void refundMovieTicketPerson(String namePerson);
    void viewPurchasedMovieTicketsPerson(String nameLogin);
    void createTicket();
}

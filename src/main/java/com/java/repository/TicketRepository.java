package com.java.repository;

public interface TicketRepository {
    void buyAMovieTicketPerson(String loginApp);
    void refundMovieTicketPerson();
    void viewPurchasedMovieTicketsPerson();
}

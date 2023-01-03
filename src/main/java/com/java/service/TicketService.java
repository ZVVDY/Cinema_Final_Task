package com.java.service;

public interface TicketService {
    void buyAMovieTicket(String loginApp);
    void refundMovieTicket(String namePerson);
    void viewPurchasedMovieTickets(String nameLogin);
    void createTicket();
}

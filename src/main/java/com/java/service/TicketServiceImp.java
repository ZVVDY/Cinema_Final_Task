package com.java.service;

import com.java.repository.TicketRepository;
import com.java.repository.TicketRepositoryImp;

public class TicketServiceImp implements TicketService{
private TicketRepository repository = new TicketRepositoryImp();

    @Override
    public void buyAMovieTicket(String loginApp) {

        repository.buyAMovieTicketPerson(loginApp);
    }

    @Override
    public void refundMovieTicket() {

    }

    @Override
    public void viewPurchasedMovieTickets() {

    }
}

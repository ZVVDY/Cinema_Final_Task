package com.java.service;

import com.java.repository.TicketRepository;
import com.java.repository.TicketRepositoryImp;

public class TicketServiceImp implements TicketService{
private TicketRepository repository = new TicketRepositoryImp();

    @Override
    // TODO

    public void buyAMovieTicket(String loginApp) {

        repository.buyAMovieTicketPerson(loginApp);
        //добавить возврат из метода
    }

    @Override
    public void refundMovieTicket(String namePerson) {
repository.refundMovieTicketPerson(namePerson);
    }

    @Override
    public void viewPurchasedMovieTickets() {

    }
}

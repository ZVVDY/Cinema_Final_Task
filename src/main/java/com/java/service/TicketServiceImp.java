package com.java.service;

import com.java.repository.TicketRepository;
import com.java.repository.TicketRepositoryImp;

public class TicketServiceImp implements TicketService {
    private TicketRepository repository = new TicketRepositoryImp();


    @Override

    public void buyAMovieTicket(String loginApp) {
        repository.buyAMovieTicketPerson(loginApp);
        System.out.println("Покупка билета выполнена");


    }

    @Override
    public void refundMovieTicket(String namePerson) {

        repository.refundMovieTicketPerson(namePerson);
    }

    @Override
    public void viewPurchasedMovieTickets(String nameLogin) {

        repository.viewPurchasedMovieTicketsPerson(nameLogin);
    }

    @Override
    public void createTicket() {
        repository.createTicket();
    }
}

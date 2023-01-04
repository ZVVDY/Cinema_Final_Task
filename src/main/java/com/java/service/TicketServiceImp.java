package com.java.service;

import com.java.repository.TicketRepository;
import com.java.repository.TicketRepositoryImp;

public class TicketServiceImp implements TicketService {

    public TicketRepository repository = new TicketRepositoryImp();

    @Override

    public void buyAMovieTicket(String loginApp, int idFilm) {

        repository.buyAMovieTicketPerson(loginApp, idFilm);
        System.out.println("Покупка билета выполнена");
    }

    @Override
    public void buyAMovieTicket() {

        repository.buyAMovieTicketPerson();
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

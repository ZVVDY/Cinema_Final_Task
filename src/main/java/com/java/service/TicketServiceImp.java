package com.java.service;

import com.java.controller.PersonController;
import com.java.repository.TicketRepository;
import com.java.repository.TicketRepositoryImp;

public class TicketServiceImp implements TicketService {
    private TicketRepository repository = new TicketRepositoryImp();


    @Override

    public void buyAMovieTicket(String loginApp) {
        PersonController personController = new PersonController();
        repository.buyAMovieTicketPerson(loginApp);
        System.out.println("Pokypka bileta vipolnena");
        personController.menuPersonController(loginApp);//добавить возврат из метода

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
    public boolean createTicket(int id, String nameFilm, int numberTicket) {
        return repository.createTicket(id,nameFilm,numberTicket);
    }
}

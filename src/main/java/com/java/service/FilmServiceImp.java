package com.java.service;

import com.java.repository.FilmRepository;
import com.java.repository.FilmRepositoryImp;

public class FilmServiceImp implements FilmService {
    public FilmRepository filmRepository = new FilmRepositoryImp();

    @Override
    public void viewEventsAndMovies(String loginApp) {
        filmRepository.viewEventsAndMovies(loginApp);
    }

    @Override
    public void editEventsAndMovies() {
        filmRepository.editEventsAndMovies();
    }

    @Override
    public void createEventsAndMovies() {
        filmRepository.createEventsAndMovies();
    }

    @Override
    public void deleteEventsAndMovies() {
        filmRepository.deleteEventsAndMovies();
    }
}

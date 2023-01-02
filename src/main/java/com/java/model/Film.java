package com.java.model;

public class Film {
    private int idMovie;
    private String nameMovie;
    private String dateAndTimeFilm;
    private int quantityTicket;

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getDateAndTimeFilm() {
        return dateAndTimeFilm;
    }

    public void setDateAndTimeFilm(String dateAndTimeFilm) {
        this.dateAndTimeFilm = dateAndTimeFilm;
    }

    public int getQuantityTicket() {
        return quantityTicket;
    }

    public void setQuantityTicket(int quantityTicket) {
        this.quantityTicket = quantityTicket;
    }

    public Film(int idMovie, String nameMovie, String dateAndTimeFilm, int quantityTicket) {
        this.idMovie = idMovie;
        this.nameMovie = nameMovie;
        this.dateAndTimeFilm = dateAndTimeFilm;
        this.quantityTicket = quantityTicket;
    }
    public Film(){

    }

    @Override
    public String toString() {
        return "Film{" +
                "idMovie=" + idMovie +
                ", nameMovie='" + nameMovie + '\'' +
                ", dateAndTimeFilm='" + dateAndTimeFilm + '\'' +
                ", quantityTicket=" + quantityTicket +
                '}';
    }
}

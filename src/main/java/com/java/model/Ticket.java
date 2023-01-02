package com.java.model;

public class Ticket {
    private Long idTicket;
    private String personTicket;
    private String personFilmTicket;
    private Integer numberPlacel;
    private Integer costTicket;
    private boolean flagTicketPurchased;
    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public String getPersonTicket() {
        return personTicket;
    }

    public void setPersonTicket(String personTicket) {
        this.personTicket = personTicket;
    }

    public String getPersonFilmTicket() {
        return personFilmTicket;
    }

    public void setPersonFilmTicket(String personFilmTicket) {
        this.personFilmTicket = personFilmTicket;
    }

    public Integer getNumberPlacel() {
        return numberPlacel;
    }

    public void setNumberPlacel(Integer numberPlacel) {
        this.numberPlacel = numberPlacel;
    }

    public Integer getCostTicket() {
        return costTicket;
    }

    public void setCostTicket(Integer costTicket) {
        this.costTicket = costTicket;
    }

    public Boolean getFlagTicketPurchased() {
        return flagTicketPurchased;
    }

    public void setFlagTicketPurchased(boolean flagTicketPurchased) {
        this.flagTicketPurchased = flagTicketPurchased;
    }



    public Ticket(Long idTicket, String personTicket, String personFilmTicket, Integer numberPlacel,
                  Integer costTicket, boolean flagTicketPurchased) {
        this.idTicket = idTicket;
        this.personTicket = personTicket;
        this.personFilmTicket = personFilmTicket;
        this.numberPlacel = numberPlacel;
        this.costTicket = costTicket;
        this.flagTicketPurchased = flagTicketPurchased;
    }
    public Ticket(){

    }
}

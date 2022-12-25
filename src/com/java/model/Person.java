package com.java.model;

public class Person {
    private Long id;
    private String loginPerson;
    private String passwordPerson;

    public Person(Long id, String loginPerson, String passwordPerson) {
        this.id = id;
        this.loginPerson = loginPerson;
        this.passwordPerson = passwordPerson;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginPerson() {
        return loginPerson;
    }

    public void setLoginPerson(String loginPerson) {
        this.loginPerson = loginPerson;
    }

    public String getPasswordPerson() {
        return passwordPerson;
    }

    public void setPasswordPerson(String passwordPerson) {
        this.passwordPerson = passwordPerson;
    }
}

package com.java.service;

import com.java.model.Person;

public interface PersonService {
    void create(Person person) throws ClassNotFoundException;
    boolean readLogin(String nameLog);
    boolean readPasswordAddLog(String logName, String passwordLog);
    void update(Person person);
    void delete(Long id);
}

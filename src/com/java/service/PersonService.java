package com.java.service;

import com.java.model.Person;

public interface PersonService {
    void create(Person person) throws ClassNotFoundException;
    boolean read(Person person);
    void update(Person person);
    void delete(Long id);
}

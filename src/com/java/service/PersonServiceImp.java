package com.java.service;

import com.java.model.Person;
import com.java.repository.PersonRepository;
import com.java.repository.PersonRepositoryImp;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;


public class PersonServiceImp implements PersonService {
    private PersonRepository personRepository = new PersonRepositoryImp();

    @Override
    public void create(Person person) throws ClassNotFoundException {
personRepository.createPerson(person);
    }

    @Override
    public boolean read(Person person) {
boolean b =personRepository.readPerson(person);
        return b;
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Long id) {

    }
}

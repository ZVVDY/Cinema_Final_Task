package com.java.service;

import com.java.model.Person;
import com.java.model.RoleClient;
import com.java.repository.PersonRepository;
import com.java.repository.PersonRepositoryImp;


public class PersonServiceImp implements PersonService {
    private PersonRepository personRepository = new PersonRepositoryImp();

    @Override
    public void create(Person person) throws ClassNotFoundException {
personRepository.createPerson(person);
    }

    @Override
    public boolean readLogin(String nameLog) {
        return personRepository.searchForARegisteredPerson(nameLog);
    }

    public boolean readPasswordAddLog(String logName, String passwordLog) {
        return personRepository.searchForARegisteredPersonPassword(logName,passwordLog);
    }

    @Override
    public String searchForAPersonInTheDatabase() {
        return personRepository.searchForAPersonInTheDatabase();
    }

//    @Override
//    public void seachRole(String userName, String personPassword) {
//        personRepository.seachRole(userName,personPassword);
//    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete() {
personRepository.deletePerson();
    }
}

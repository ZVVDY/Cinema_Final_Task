package com.java.repository;

import com.java.model.Person;

public interface PersonRepository {
    void createPerson (Person person) throws ClassNotFoundException;

    boolean searchForARegisteredPerson(String userName);

    boolean readPerson(Person person);
    void updatePerson(Person person);
    void deletePerson(Long id);

}

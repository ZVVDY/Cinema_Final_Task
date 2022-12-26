package com.java.repository;

import com.java.model.Person;

public interface PersonRepository {
    void createPerson (Person person) throws ClassNotFoundException;

    boolean searchForARegisteredPerson(String userName);

    boolean searchForARegisteredPersonPassword(String userName, String personPassword);
    void updatePerson(Person person);
    void deletePerson(Long id);

}

package com.java.repository;

import com.java.model.Person;
import com.java.model.RoleClient;

public interface PersonRepository {
    void createPerson (Person person) throws ClassNotFoundException;

    boolean searchForARegisteredPerson(String userName);

    boolean searchForARegisteredPersonPassword(String userName, String personPassword);
    //void seachRole(String userName, String personPassword);
    void updatePerson(Person person);
    void deletePerson(Long id);

}

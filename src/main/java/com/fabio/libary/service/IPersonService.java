package com.fabio.libary.service;

import com.fabio.libary.model.person.Person;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface IPersonService {

    List<Person> getAll();

    Person getPersonById(String personId) throws EmptyResultDataAccessException;

}

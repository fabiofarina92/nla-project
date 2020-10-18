package com.fabio.libary.service;

import com.fabio.libary.model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Person> getAll() {
        String sql = "SELECT * FROM people";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Person(
                                rs.getString("id"),
                                rs.getString("name"),
                                rs.getString("phone_number"),
                                rs.getString("email_address")
                        )
        );
    }

    @Override
    public Person getPersonById(String personId) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM PEOPLE where ID = ?";

        return jdbcTemplate.queryForObject(
                sql,
                new String[]{personId},
                (rs, rowNum) ->
                        new Person(
                                rs.getString("id"),
                                rs.getString("name"),
                                rs.getString("phone_number"),
                                rs.getString("email_address")
                        )
        );
    }


}

package org.example.dao;

import org.example.dao.Mapper.PersonMapper;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);

    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public void addNewPerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES (1, ?, ?, ?)", person.getName(), person.getAge(), person.getMail());
    }

    public void updatePersonById(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, mail=? WHERE id=?", person.getName(), person.getAge(), person.getMail(), id);
    }

    public void deletePersonById(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

}

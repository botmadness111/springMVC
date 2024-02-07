package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> getPersonByMail(String mail) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE mail=?", new Object[]{mail}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void addNewPerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, age, mail, address) VALUES (?, ?, ?, ?)", person.getName(), person.getAge(), person.getMail(), person.getAddress());
    }

    public void updatePersonById(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, mail=?, address=? WHERE id=?", person.getName(), person.getAge(), person.getMail(), person.getAddress(), id);
    }

    public void deletePersonById(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    ///////////////////////////////////////////////////////////////////////////////////
    /////////Тест Операция одним Пакетом (с 1000 данными) VS 1000 запрос SQL///////////
    ///////////////////////////////////////////////////////////////////////////////////

    public List<Person> create1000person() {
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Person " + i, "Person " + i, "Person " + i + "@mail.ru", 19));
        }

        return people;
    }

    public void test1000query() {
        List<Person> people = create1000person();

        long startTime = System.currentTimeMillis();

        for (Person person : people) {
            jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ? ,?)", person.getId(), person.getName(), person.getAge(), person.getMail());
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Time is: " + (endTime - startTime));

    }

    public void test1queryUsingBatches() {
        List<Person> people = create1000person();

        long startTime = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES (?, ?, ? ,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, people.get(i).getId());
                preparedStatement.setString(2, people.get(i).getName());
                preparedStatement.setInt(3, people.get(i).getAge());
                preparedStatement.setString(4, people.get(i).getMail());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });

        long endTime = System.currentTimeMillis();

        System.out.println("Time is: " + (endTime - startTime));
    }
}

//TRASH
package org.example.dao;

import org.example.models.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HumanDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public HumanDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Human> getPeople() {
        return jdbcTemplate.query("SELECT * FROM Human", new BeanPropertyRowMapper<>(Human.class));
    }

    public void add(Human human) {
        jdbcTemplate.update("INSERT INTO Human(fio, year_of_birth) VALUES(?, ?)", human.getFio(), human.getYear_of_birth());
    }

    public Human getHuman(int id) {
        return jdbcTemplate.query("SELECT * FROM Human WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Human.class)).stream().findAny().orElse(null);
    }

    public void update(Human human, int id) {
        jdbcTemplate.update("UPDATE Human SET fio=?, year_of_birth=? WHERE id=?", human.getFio(), human.getYear_of_birth(), id);
    }

    public Optional<Human> getHuman(String fio) {
        return jdbcTemplate.query("SELECT * FROM Human WHERE fio=?", new Object[]{fio}, new BeanPropertyRowMapper<>(Human.class)).stream().findAny();
    }
}

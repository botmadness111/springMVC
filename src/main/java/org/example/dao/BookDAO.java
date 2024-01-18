package org.example.dao;

import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year_of_release) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear_of_release());
    }

    public void updateBook(Book book, int id) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year_of_release=? WHERE id=?", book.getName(), book.getAuthor(), book.getYear_of_release(), id);
    }

    public Optional<Book> getBook(String name) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }
}

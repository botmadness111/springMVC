package org.example.dao;

import org.example.models.Book;
import org.example.models.Human;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Book> getBooks() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
        return null;
    }

    public Book getBook(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
        return null;
    }

    public void addBook(Book book) {
//        jdbcTemplate.update("INSERT INTO Book(name, author, year_of_release) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear_of_release());

    }

    public void updateBook(Book book, int id) {
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year_of_release=? WHERE id=?", book.getName(), book.getAuthor(), book.getYear_of_release(), id);

    }

    public Optional<Book> getBook(String name) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
        return null;
    }

    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);

    }

    public void setHuman(int id_book, int id_human) {
//        jdbcTemplate.update("UPDATE Book SET id_human=? WHERE id=?", id_human, id_book);

    }

    public Optional<Human> getHuman(int id) {
//        return jdbcTemplate.query("SELECT * FROM Human WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Human.class)).stream().findAny();
        return null;
    }

    public void unsetHuman(int id) {
//        jdbcTemplate.update("UPDATE Book SET id_human=? WHERE id=?", null, id);
//    }
    }
}

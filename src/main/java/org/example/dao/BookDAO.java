package org.example.dao;

import org.example.models.Book;
import org.example.models.Human;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> getBooks() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Book getBook(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Transactional(readOnly = false)
    public void addBook(Book book) {
//        jdbcTemplate.update("INSERT INTO Book(name, author, year_of_release) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear_of_release());
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    @Transactional(readOnly = false)
    public void updateBook(Book book, int id) {
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year_of_release=? WHERE id=?", book.getName(), book.getAuthor(), book.getYear_of_release(), id);
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("update Book set name=:name, author=:author, year_of_release=:year_of_release where id=:id").setParameter("author", book.getAuthor()).setParameter("name", book.getName()).setParameter("year_of_release", book.getYear_of_release()).setParameter("id", id).executeUpdate();
    }

    @Transactional(readOnly = true)
    public Optional<Book> getBook(String name) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Book b where b.name=name", Book.class).uniqueResultOptional();
    }

    @Transactional(readOnly = false)
    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        session.remove(book);
    }

    @Transactional(readOnly = false)
    public void setHuman(int id_book, int id_human) {
//        jdbcTemplate.update("UPDATE Book SET id_human=? WHERE id=?", id_human, id_book);
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("update Book set id_human=:id_human where id=:id_book").setParameter("id_human", id_human).setParameter("id_book", id_book).executeUpdate();
    }

    @Transactional(readOnly = true)
    public Optional<Human> getHuman(int id) {
//        return jdbcTemplate.query("SELECT * FROM Human WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Human.class)).stream().findAny();
        Session session = sessionFactory.getCurrentSession();
        return Optional.of(session.get(Human.class, id));
    }

    @Transactional(readOnly = false)
    public void unsetHuman(int id) {
//        jdbcTemplate.update("UPDATE Book SET id_human=? WHERE id=?", null, id);
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("update Book set id_human=:id_human where id=:id").setParameter("id_human", null).setParameter("id", id).executeUpdate();
    }
}

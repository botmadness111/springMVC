package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    @NotEmpty(message = "Name should be not Empty")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Author should be not Empty")
    private String author;

    @Column(name = "year_of_release")
    @NotNull(message = "Year of release should be not Empty")
    private Integer year_of_release;

    private Integer id_human;

    public Book(String name, String author, Integer year_of_release) {
        this.name = name;
        this.author = author;
        this.year_of_release = year_of_release;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear_of_release() {
        return year_of_release;
    }

    public void setYear_of_release(Integer year_of_release) {
        this.year_of_release = year_of_release;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_human() {
        return id_human;
    }

    public void setId_human(Integer id_human) {
        this.id_human = id_human;
    }
}

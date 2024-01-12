package org.example.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;
    @Size(min = 2, max = 30, message = "Size of Name should be between 2 to 30 characters")
    private String name;
    @Size(min = 2, max = 30, message = "Size of Name should be between 2 to 30 characters")
    private String surName;
    @Min(value = 0, message = "Age should be bigger then 0")
    private Integer age;
    @Email(message = "email should be valid")
    private String mail;

    public Person(int id, String name, String surName, String mail, Integer age) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.mail = mail;
        this.age = age;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

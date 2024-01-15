package org.example.models;

import jakarta.validation.constraints.*;

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
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Address should be in this format: Country, City, post numbers (6 digits)")
    private String address;

    public Person(int id, String name, String surName, String mail, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.mail = mail;
        this.age = age;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

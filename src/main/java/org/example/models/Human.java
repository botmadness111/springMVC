package org.example.models;

import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

@Component
public class Human {
    Integer id;

    @NotEmpty(message = "FIO should be not empty")
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-z]+\\s[А-ЯЁA-Z][а-яёa-z]+\\s[А-ЯЁA-Z][а-яёa-z]+$", message = "FIO is Surname Name Patronymic")
    private String fio;

    @Min(value = 1900, message = "Year of birth should be between 1900 and 2024")
    @Max(value = 2024, message = "Year of birth should be between 1900 and 2024")
    @NotNull(message = "Year of birth should be not empty")
    private Integer year_of_birth;

    public Human() {
    }

    public Human(String fio, Integer year_of_birth) {
        this.fio = fio;
        this.year_of_birth = year_of_birth;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(Integer year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package org.example.dao;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    int PEOPLE_COUNTER;
    List<Person> people;

    public PersonDAO() {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNTER, "Andrey", "Korolev"));
        people.add(new Person(++PEOPLE_COUNTER, "Egor", "Bekasov"));
        people.add(new Person(++PEOPLE_COUNTER, "Aydan", "Galeev"));
    }

    public Person getPersonById(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public List<Person> getPeople() {
        return people;
    }

}

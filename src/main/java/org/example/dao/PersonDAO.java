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
        people.add(new Person(++PEOPLE_COUNTER, "Andrey", "Korolev", "Andrey@mail.ru", 18));
        people.add(new Person(++PEOPLE_COUNTER, "Egor", "Bekasov", "Egor@mail.ru", 12));
        people.add(new Person(++PEOPLE_COUNTER, "Aydan", "Galeev", "Aydan@mail.ru", 29));
    }

    public Person getPersonById(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public List<Person> getPeople() {
        return people;
    }

    public void addNewPerson(Person person) {
        person.setId(++PEOPLE_COUNTER);
        people.add(person);
    }

    public void updatePersonById(int id, Person person) {
        Person personToBeUpdated = getPersonById(id);

        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setSurName(person.getSurName());
        personToBeUpdated.setMail(person.getMail());
        personToBeUpdated.setAge(person.getAge());
    }

    public void deletePersonById(int id) {
        people.removeIf(person -> person.getId() == id);
    }

}

package org.example.services;

import org.example.models.Person;
import org.example.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPersonById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    @Transactional
    public void addNewPerson(Person person) {
        person.setCreated_at(new Date());
        personRepository.save(person);
    }

    @Transactional
    public void updatePersonById(int id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }

    @Transactional
    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }

    public List<Person> findByAgeGreaterThan(int age) {
        return personRepository.findByAgeGreaterThan(age);
    }

    public List<Person> findByNameOrMail(String name, String mail) {
        return personRepository.findByNameOrMail(name, mail);
    }
}

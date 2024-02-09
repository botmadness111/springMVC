package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {
    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class);

        //1 Запрос
        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();

        //N запросов
        //Hibernate: select i1_0.person_id,i1_0.id,i1_0.name from item i1_0 where i1_0.person_id=?
        //Eager не поможет, тк n запросов произойдут вместе с первым запросом.
        for (Person person : people) {
            //System.out.println("Person " + person.getName() + " has: " + person.getItems());
            System.out.println(person.getItems());
        }
    }
    @Transactional
    public void testNPlus1Optimized() {
        Session session = entityManager.unwrap(Session.class);

        //1 Запрос
        //объединям две таблицы Person и Item.
        //операция FETCH - подгрузит items в объект person.
        //Hibernate понимает это, потому что стоит анноатция @OneToMany
        List<Person> people = session.createQuery("select p from Person p left join fetch p.items", Person.class).getResultList();

        //0 запросов, тк все уже подгружено
        for (Person person : people) {
            //System.out.println("Person " + person.getName() + " has: " + person.getItems());
            System.out.println(person.getItems());
        }
    }
}

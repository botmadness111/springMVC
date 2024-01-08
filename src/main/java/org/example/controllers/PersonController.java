package org.example.controllers;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/people")
    public String getPeoplePage(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "/person/people";
    }

    @GetMapping("/people/{id}")
    public String getPersonPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "/person/person";
    }

    @GetMapping("/people/add")
    public String getAddNewPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "/person/addNewPerson";
    }

    @PostMapping("/people")
    public String postNewPersonIntoPeoplePage(@ModelAttribute("person") Person person) {
        personDAO.addNewPerson(person);
        return "redirect:/people";
    }
}

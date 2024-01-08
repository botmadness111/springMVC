package org.example.controllers;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getPeoplePage(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "/person/people";
    }

    @GetMapping("/{id}")
    public String getPersonPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "/person/person";
    }

    @GetMapping("/add")
    public String getAddNewPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "/person/addNewPerson";
    }

    @PostMapping
    public String postNewPersonIntoPeoplePage(@ModelAttribute("person") Person person) {
        personDAO.addNewPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String getEditPersonPage(@PathVariable("id") int id, Model model) {

        model.addAttribute(personDAO.getPersonById(id));

        return "/person/editPerson";
    }

    @PatchMapping("/{id}")
    public String updatePersonById(@PathVariable("id") int id, @ModelAttribute Person person) {
        personDAO.updatePersonById(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePersonById(@PathVariable("id") int id) {
        personDAO.deletePersonById(id);

        return "redirect:/people";
    }
}

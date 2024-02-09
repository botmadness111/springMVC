package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.example.repositories.PersonRepository;
import org.example.services.ItemService;
import org.example.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;

@Controller
@RequestMapping("/people")
public class PersonController {

    PersonService personService;
    ItemService itemService;

    PersonDAO personDAO;

    @Autowired
    public PersonController(PersonService personService, ItemService itemService, PersonDAO personDAO) {
        this.personService = personService;
        this.itemService = itemService;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getPeoplePage(Model model) {
        model.addAttribute("people", personService.getPeople());

        personDAO.testNPlus1Optimized();

        return "/person/people";
    }

    @GetMapping("/{id}")
    public String getPersonPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.getPersonById(id));
        return "/person/person";
    }

    @GetMapping("/add")
    public String getAddNewPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "/person/addNewPerson";
    }

    @PostMapping
    public String postNewPersonIntoPeoplePage(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/person/addNewPerson";
        }

        personService.addNewPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String getEditPersonPage(@PathVariable("id") int id, Model model) {

        model.addAttribute(personService.getPersonById(id));

        return "/person/editPerson";
    }

    @PatchMapping("/{id}")
    public String updatePersonById(@PathVariable("id") int id, @ModelAttribute Person person) {
        personService.updatePersonById(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePersonById(@PathVariable("id") int id) {
        personService.deletePersonById(id);

        return "redirect:/people";
    }
}

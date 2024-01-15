package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.example.util.validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    PersonDAO personDAO;
    PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
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
    public String postNewPersonIntoPeoplePage(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/person/addNewPerson";
        }

        personDAO.addNewPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String getEditPersonPage(@PathVariable("id") int id, Model model) {

        model.addAttribute(personDAO.getPersonById(id));

        return "/person/editPerson";
    }

    @PatchMapping("/{id}")
    public String updatePersonById(@PathVariable("id") int id, @Valid @ModelAttribute Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()){
            return "redirect:/people/edit/{id}";
        }

        personDAO.updatePersonById(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePersonById(@PathVariable("id") int id) {
        personDAO.deletePersonById(id);

        return "redirect:/people";
    }
}

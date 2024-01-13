package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String postNewPersonIntoPeoplePage(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
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
    public String updatePersonById(@PathVariable("id") int id, @ModelAttribute Person person) {
        personDAO.updatePersonById(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePersonById(@PathVariable("id") int id) {
        personDAO.deletePersonById(id);

        return "redirect:/people";
    }

    /////////////////////////////////////////////////////////////////////////////////////
    //    /////////Тест Операция одним Пакетом (с 1000 данными) VS 1000 запрос SQL///////////
    //    ///////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/chooseTest")
    public String getChooseTestPage(){
        return "/batchTest/chooseTest";
    }

    @GetMapping("/withoutBatch")
    public String getWithoutBatchPage(Model model){
        personDAO.test1000query(); //Загружаем в бд 1000 новых Person, используя 1000 запросов
        model.addAttribute("people", personDAO.getPeople());

        return "batchTest/withoutBatch";
    }

    @GetMapping("/withBatch")
    public String getWithBatchPage(Model model){
        personDAO.test1queryUsingBatches(); //Загружаем в бд 1000 новых Person, используя 1 пакет, в котором 1000 запросов
        model.addAttribute("people", personDAO.getPeople());

        return "batchTest/withBatch";
    }

}

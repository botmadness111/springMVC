package org.example.controllers;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getChangeAdminPage(Model model, @ModelAttribute("person") Person person) {
        //ModelAttribute создал new Person person = null;
        model.addAttribute("people", personDAO.getPeople());
        return "/admin/changeAdmin";
    }

    @PatchMapping("/change")
    public String updateAdmin(@ModelAttribute Person person) {
        System.out.println(person.getId());

        return "redirect:/people";
    }

}

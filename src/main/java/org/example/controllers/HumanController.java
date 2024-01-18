package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.HumanDAO;
import org.example.models.Human;
import org.example.util.validators.HumanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/human")
public class HumanController {

    HumanDAO humanDAO;
    HumanValidator humanValidator;

    @Autowired
    public HumanController(HumanDAO humanDAO, HumanValidator humanValidator) {
        this.humanDAO = humanDAO;
        this.humanValidator = humanValidator;
    }

    @GetMapping("/all")
    public String getPeoplePage(Model model) {
        model.addAttribute("people", humanDAO.getPeople());

        return "/human/people";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("human", new Human());

        return "/human/addHuman";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("human") @Valid Human human, BindingResult bindingResult) {
        humanValidator.validate(human, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/human/addHuman";
        }

        humanDAO.add(human);

        return "redirect:/human/all";
    }

    @GetMapping("/edit/{id}")
    public String getUpdatePage(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("human", humanDAO.getHuman(id));

        return "/human/editHuman";
    }

    @PatchMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute Human human, BindingResult bindingResult, @PathVariable("id") Integer id) {
        humanValidator.validate(human, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/human/editHuman";
        }

        humanDAO.update(human, id);

        return "redirect:/human/all";
    }

    @GetMapping("/{id}")
    public String getHumanPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("human", humanDAO.getHuman(id));

        return "/human/human";
    }
}

package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.BookDAO;
import org.example.dao.HumanDAO;
import org.example.models.Book;
import org.example.models.Human;
import org.example.util.validators.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    BookDAO bookDAO;
    HumanDAO humanDAO;
//    BookValidator bookValidator;


    @Autowired
    public BookController(BookDAO bookDAO, BookValidator bookValidator, HumanDAO humanDAO) {
        this.bookDAO = bookDAO;
//        this.bookValidator = bookValidator;
        this.humanDAO = humanDAO;
    }


    @GetMapping("/all")
    public String getBooksPage(Model model) {
        model.addAttribute("books", bookDAO.getBooks());

        return "/book/books";
    }

    @GetMapping("/add")
    public String getAddBookPage(Model model) {
        model.addAttribute("book", new Book());

        return "/book/addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute @Valid Book book, BindingResult bindingResult) {
//        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/book/addBook";
        }

        bookDAO.addBook(book);

        return "redirect:/book/all";
    }

    @GetMapping("/edit/{id}")
    public String getUpdatePage(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.getBook(id));

        return "/book/editBook";
    }

    @PatchMapping("/edit/{id}")
    public String updateBook(@ModelAttribute @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
//        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/book/editBook";
        }

        bookDAO.updateBook(book, id);

        return "redirect:/book/all";
    }

    @GetMapping("/{id}")
    public String getBookPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.getBook(id));
        model.addAttribute("people", humanDAO.getPeople());

        Integer id_human = bookDAO.getBook(id).getId_human();
        if (id_human == null) model.addAttribute("human", new Human());
        else model.addAttribute("human", bookDAO.getHuman(bookDAO.getBook(id).getId_human()).orElse(new Human()));

        return "/book/book";
    }

    @PatchMapping("/setHuman/{id}")
    public String setHuman(@ModelAttribute Human human, @PathVariable("id") int id) {
        System.out.println(human.getId());
        bookDAO.setHuman(id, human.getId());

        return "redirect:/book/all";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@ModelAttribute Book book, @PathVariable("id") int id) {
        bookDAO.delete(id);

        return "redirect:/book/all";
    }

    @PatchMapping("/release/{id}")
    public String release(@PathVariable("id") int id) {
        bookDAO.unsetHuman(id);

        return "redirect:/book/all";
    }
}

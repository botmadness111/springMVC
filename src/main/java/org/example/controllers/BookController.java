package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.BookDAO;
import org.example.models.Book;
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
    BookValidator bookValidator;

    @Autowired
    public BookController(BookDAO bookDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
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
        bookValidator.validate(book, bindingResult);

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
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/book/editBook";
        }

        bookDAO.updateBook(book, id);

        return "redirect:/book/all";
    }
}

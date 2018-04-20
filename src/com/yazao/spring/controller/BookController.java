package com.yazao.spring.controller;

import com.yazao.spring.model.Book;
import com.yazao.spring.model.Category;
import com.yazao.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookservice;

    @RequestMapping(value = "/input-book")
    public String inputBook(Model model) {
        List<Category> categories = bookservice.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("book", new Book());
        return "BookAddForm";
    }

    @RequestMapping(value = "/list-books")
    public String listBooks(Model model) {
        List<Book> books = bookservice.getAllBooks();
        model.addAttribute("books", books);
        return "BookList";
    }

    @RequestMapping(value = "/edit-book/{id}")
    public String editBook(@PathVariable long id, Model model) {
        List<Category> categories = bookservice.getAllCategories();
        model.addAttribute("categories", categories);
        Book book = bookservice.get(id);
        model.addAttribute("book", book);
        return "BookEditForm";
    }


}

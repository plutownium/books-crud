package com.example.bookscrud.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.bookscrud.util.IdField;


@RestController
//@Controller
@RequestMapping(path = "/api/v1/books")
public class BooksController {
    private final BookService BookService;

    @Autowired // magic
    public BooksController(BookService BookService) {
        this.BookService = BookService;
    }


    @GetMapping(value = "/all")
    public List<Book> getBooks() {
        return BookService.getBooks();

    }

    @PostMapping(value = "/add")
    public Book createBook(@RequestBody Book book) {
        System.out.println(book);
        return BookService.createBook(book);
    }

    @DeleteMapping(value = "/delete")
    public String deleteBook(@RequestBody IdField id) {
        return BookService.deleteBook(id.getId());
    }


    @GetMapping(value = "/health")
    public String Health() {
        return "Onlineeee";
    }
}
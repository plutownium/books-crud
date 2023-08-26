package com.example.bookscrud.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.bookscrud.util.IdField;


@RestController
@RequestMapping(path = "api/v1/books")
public class BooksController {
    private final BookService BookService;

    @Autowired // magic
    public BooksController(BookService BookService) {
        this.BookService = BookService;
    }


    @GetMapping(value = "/")
    public List<Book> getBooks() {
        return BookService.getBooks();
        // return List.of(Book, bart);
    }

    @PostMapping(value = "/")
    public Book createBook(@RequestBody Book Book) {
        System.out.println("here! 37rm");
        System.out.println(Book);
        System.out.println("here! 39rm");
        // return new Book(1, "temp", "temp", )
        return BookService.createBook(Book);
    }

    @DeleteMapping(value = "/")
    public String deleteBook(@RequestBody IdField id) {
        System.out.println("here is id");
        System.out.println(id);
        System.out.println("================================");
        return BookService.deleteBook(id.getId());
    }


    @GetMapping(value = "/health")
    public String Health() {
        return "Online";
    }
}
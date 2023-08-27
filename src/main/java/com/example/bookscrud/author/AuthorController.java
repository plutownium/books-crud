package com.example.bookscrud.author;

import com.example.bookscrud.util.IdField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@Controller
@RequestMapping(path = "/api/v1/authors")
public class AuthorController {
    private final AuthorService AuthorService;

    @Autowired // magic
    public AuthorController(AuthorService AuthorService) {
        this.AuthorService = AuthorService;
    }


    @GetMapping(value = "/all")
    public List<Author> getAuthors() {
        return AuthorService.getAuthors();

    }

    @PostMapping(value = "/add")
    public Author createAuthor(@RequestBody Author author) {
        System.out.println(author);
        return AuthorService.createAuthor(author);
    }

    @DeleteMapping(value = "/delete")
    public String deleteAuthor(@RequestBody IdField id) {
        return AuthorService.deleteAuthor(id.getId());
    }


    @GetMapping(value = "/health")
    public String Health() {
        return "Onlineeee";
    }
}
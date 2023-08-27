package com.example.bookscrud.author;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepositoryImpl BookRepository;

    @Autowired
    public AuthorService(AuthorRepositoryImpl BookRepository) {
        this.BookRepository = BookRepository;
    }

    public List<Author> getBooks() {
        return BookRepository.getAll();
    }

    public Author createBook(Author Book) {
        return BookRepository.create(Book);
    }

    public String deleteBook(int id) {
        return BookRepository.delete(id);
    }
}
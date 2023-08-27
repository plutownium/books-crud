package com.example.bookscrud.author;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepositoryImpl AuthorRepository;

    @Autowired
    public AuthorService(AuthorRepositoryImpl AuthorRepository) {
        this.AuthorRepository = AuthorRepository;
    }

    public List<Author> getAuthors() {
        return AuthorRepository.getAll();
    }

    public Author createAuthor(Author Author) {
        return AuthorRepository.create(Author);
    }

    public String deleteAuthor(int id) {
        return AuthorRepository.delete(id);
    }
}
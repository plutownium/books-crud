package com.example.bookscrud.author;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository {

    public char foo();

    public void createTableIfNotExists();

    public List<Author> getAll();

    public Author create(Author Book);

    public String delete(int id);

    public void destroyAll();

    public boolean emailExists(String email);
}
package com.example.bookscrud.book;


import java.util.List;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  {

    public char foo();

    public void createTableIfNotExists();

    public List<Book> getAll();

    public Book create(Book Book);

    public String delete(int id);

    public void destroyAll();

    public boolean emailExists(String email);
}
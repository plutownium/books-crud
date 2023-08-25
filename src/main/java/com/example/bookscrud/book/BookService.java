package com.example.bookscrud.book;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepositoryImpl BookRepository;

    @Autowired
    public BookService(BookRepositoryImpl BookRepository) {
        this.BookRepository = BookRepository;
    }

    public List<Book> getBooks() {
        return BookRepository.getAll();
    }

    public Book createBook(Book Book) {
        return BookRepository.create(Book);
    }

    public String deleteBook(int id) {
        return BookRepository.delete(id);
    }
}
package com.example.bookscrud.util;

import java.util.ArrayList;
import java.util.List;

import com.example.bookscrud.author.Author;
import com.example.bookscrud.book.Book;

public class Seeds {

    public Seeds() {
        //
    }

    public List<Author> getSeedAuthors() {
        List<Author> seedAuthors = new ArrayList<Author>();
        seedAuthors.add(new Author(0, "Scott", "Adams", 1996));
        seedAuthors.add(new Author(1, "Naval", "Ravikant", 2017));
        seedAuthors.add(new Author(2, "Nassim", "Taleb", 2008));
        seedAuthors.add(new Author(3, "Paul", "Graham", 2003));
        seedAuthors.add(new Author(4, "Douglas", "Hofstadter", 2014));
        seedAuthors.add(new Author(5, "Tim", "Ferris", 2005));
        return seedAuthors;
    }

    public List<Book> getSeedBooks() {
        List<Book> seedBooks = new ArrayList<Book>();
        seedBooks.add(new Book(0, "Hats For Cats", 1999, false, 0));
        seedBooks.add(new Book(1, "Jazz Is Big Now", 2001, false, 1));
        seedBooks.add(new Book(2, "Fireplaces In Europe", 2003, false, 2));
        seedBooks.add(new Book(3, "Modern Castles", 2005, false,3 ));
        seedBooks.add(new Book(4, "Short Titles for Books", 2007, false,4 ));
        seedBooks.add(new Book(5, "Overwatch: Strategies", 2009, false,5 ));
        seedBooks.add(new Book(6, "Chrono Trigger", 2011, false,5 ));
        return seedBooks;
    }
}

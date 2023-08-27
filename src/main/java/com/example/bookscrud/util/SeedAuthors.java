package com.example.bookscrud.util;

import com.example.bookscrud.author.Author;

import java.util.ArrayList;
import java.util.List;

public class SeedAuthors {

    public SeedAuthors() {
        //
    }

    public List<Author> getSeedAuthors() {
        List<Author> seedAuthors = new ArrayList<Author>();
        seedAuthors.add(new Author(0, "Scott Adams", 1996));
        seedAuthors.add(new Author(1, "Naval Ravikant", 2017));
        seedAuthors.add(new Author(2, "Nassim Taleb", 2008));
        seedAuthors.add(new Author(3, "Paul Graham", 2003));
        seedAuthors.add(new Author(4, "Douglas Hofstadter", 2014));
        seedAuthors.add(new Author(5, "Tim Ferris", 2005));
        return seedAuthors;
    }
}

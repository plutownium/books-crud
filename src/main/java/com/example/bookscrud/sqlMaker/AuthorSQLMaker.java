package com.example.bookscrud.sqlMaker;

import com.example.bookscrud.author.Author;

import java.sql.SQLException;


public class AuthorSQLMaker {
    public String createAuthorTableIfNotExists() {
        return String.format("CREATE TABLE IF NOT EXISTS Authors " +
                "(ID                     SERIAL PRIMARY KEY," +
                " FIRSTNAME              TEXT    NOT NULL, " +
                " LASTNAME               TEXT    NOT NULL, " +
                " PUBLISHED              INT     NOT NULL);");
    }

    public String createAuthor(String firstName, String lastName, Integer published) {
        return String.format("INSERT INTO Authors (firstname, lastname, published) " +
                "VALUES ('%s', '%s', '%s') RETURNING id;", firstName, lastName, published);
    }

    public String createAuthorFromObj(Author author) {
        return String.format("INSERT INTO Authors (firstname, lastname, published) " +
                "VALUES ('%s', '%s', '%s') RETURNING id;", author.getFn(), author.getLn(), author.getYearPublished());
    }

    public String count() {
        return "SELECT count(*) AS exact_count FROM Authors;";
    }

    public String getAllAuthors() throws SQLException {
        return "SELECT * FROM Authors";
    }

    public String deleteAuthorByName(String name) {
        return String.format("DELETE FROM Authors WHERE firstname=%s;", name);
    }


    public String deleteAll() {
        return "TRUNCATE Authors CASCADE;";
    }
}

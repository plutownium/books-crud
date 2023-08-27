package com.example.bookscrud.util;

import com.example.bookscrud.author.Author;
import com.example.bookscrud.book.Book;
import com.example.bookscrud.db.Database;
import com.example.bookscrud.sqlMaker.AuthorSQLMaker;
import com.example.bookscrud.sqlMaker.BookSQLMaker;

import java.sql.SQLException;

public class Seeder {

    private Database db;
    public Seeder(Database db) {
        // no-arg default constructor;
        this.db = db;
    }

    public void seedAuthor(Author authorToWrite, AuthorSQLMaker authorSQL) throws SQLException {
        String addAuthorCmd = authorSQL.createAuthorFromObj(authorToWrite);
        this.db.operate(addAuthorCmd);
    }

    public void seedBook(Book bookToWrite, BookSQLMaker bookSQL) throws SQLException {
        String addBookCmd = bookSQL.createBookFromObj(bookToWrite);
        this.db.operate(addBookCmd);
    }
//
//    public void seedGuest(Guest guest) {
//        //
//    }
//
//    public void seedHighlight(Highlight highlight) {
//        //
//    }
}

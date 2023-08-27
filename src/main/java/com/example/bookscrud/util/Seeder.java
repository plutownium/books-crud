package com.example.bookscrud.util;

import com.example.bookscrud.BooksCrudApplication;
import com.example.bookscrud.author.Author;
import com.example.bookscrud.book.Book;
import com.example.bookscrud.db.Database;
import com.example.bookscrud.sqlMaker.*;

import java.sql.SQLException;

public class Seeder {

    private Database db;
    public Seeder(Database db) {
        // no-arg default constructor;
        this.db = db;
    }

    public void createTablesIfNotExists() throws SQLException {
        AuthorSQLMaker aSQL = new AuthorSQLMaker();
        BookSQLMaker bSQL = new BookSQLMaker();
        GuestSQLMaker gSQL = new GuestSQLMaker();
        HighlightSQLMaker hSQL = new HighlightSQLMaker();
        AuthorshipSQLMaker auSQL = new AuthorshipSQLMaker();
        String t1 = aSQL.createAuthorTableIfNotExists();
        String t2 = bSQL.createBookTableIfNotExists();
        String t3 = gSQL.createGuestTableIfNotExists();
        String t4 = hSQL.createHighlightTableIfNotExists();
        String t5 = auSQL.createAuthorshipTableIfNotExists();
        this.db.operateUpdate(t1);
        this.db.operateUpdate(t2);
        this.db.operateUpdate(t3);
        this.db.operateUpdate(t4);
        this.db.operateUpdate(t5);
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

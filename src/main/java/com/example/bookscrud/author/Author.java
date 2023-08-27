package com.example.bookscrud.author;

import com.example.bookscrud.db.Database;
import com.example.bookscrud.sqlMaker.AuthorshipSQLMaker;
import com.example.bookscrud.sqlMaker.BookSQLMaker;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Author {
    private ResultSet author;
    private Database pg;
    //
    private Integer id;
    private String name;
    private Integer authorId;
    private Integer year;
    private Boolean rented;

    public Author(Database pg, String name, Integer authorId, Integer published) throws SQLException {
        this.name = name;
        this.authorId = authorId;
        this.year = published;
        BookSQLMaker bookTool = new BookSQLMaker();
        String query = bookTool.createBook(name, published);
        ResultSet book = pg.operate(query);
        this.setBook(book);
        this.setPg(pg);
        // establish link
        AuthorshipSQLMaker authorshipTool = new AuthorshipSQLMaker();
        String linkingQuery = authorshipTool.createAuthorship(authorId, this.getId());
        pg.operateUpdate(linkingQuery);
    }

    // for creating books that already exist in the db
    public Author(Integer id, String name, Integer published, boolean rented)  {
        this.id = id;
        this.name = name;
        this.year = published;
        this.rented = rented;
    }

    private void setBook(ResultSet book) {
        this.book = book;
    }

    private void setPg(Database pg) {
        this.pg = pg;
    }

    private Database getPg() {
        return this.pg;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() throws SQLException {
        if (this.id != null) {
            return this.id;
        }
        this.book.next();
        String theId = this.book.getString(1);
        Integer id = Integer.parseInt(theId);
        this.setId(id);
        return id;
    }

    public void addAuthor(Integer authorId) throws SQLException {
        AuthorshipSQLMaker authorshipTool = new AuthorshipSQLMaker();
        String linkingQuery = authorshipTool.createAuthorship(authorId, this.getId());
        Database pg = this.getPg();
        pg.operateUpdate(linkingQuery);
        BookSQLMaker bookTool = new BookSQLMaker();
        ResultSet linked = pg.operate(bookTool.getBooksForAuthor(authorId));
        this.setBook(linked);

    }

    public String toString() {
        return String.format("Book {name: %s, authorId: %s, name: %s, rented: %s }",
                name, authorId, name, rented);
    }
}

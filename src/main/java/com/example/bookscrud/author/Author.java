package com.example.bookscrud.author;

import com.example.bookscrud.db.Database;
import com.example.bookscrud.sqlMaker.AuthorshipSQLMaker;
import com.example.bookscrud.sqlMaker.BookSQLMaker;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Author {
    private ResultSet author;

    //
    private Integer id;
    private String fn;
    private String ln;
    private Integer authorId;
    private Integer firstPublished;
    private Boolean rented;

    // saying no to author constructors automatically doing stuff with the db
//    public Author(String name, Integer authorId, Integer published) throws SQLException {
//        this.name = name;
//        this.authorId = authorId;
//        this.year = published;
//        BookSQLMaker bookTool = new BookSQLMaker();
//        String query = bookTool.createBook(name, published);
//        ResultSet book = pg.operate(query);
//        this.setBook(book);
//        this.setPg(pg);
//        // establish link
//        AuthorshipSQLMaker authorshipTool = new AuthorshipSQLMaker();
//        String linkingQuery = authorshipTool.createAuthorship(authorId, this.getId());
//        pg.operateUpdate(linkingQuery);
//    }

    // for creating books that already exist in the db
    public Author(Integer id, String fn, String ln, Integer firstPublished)  {
        this.id = id;
        this.fn = fn;
        this.ln = ln;
        this.firstPublished = firstPublished;
    }

    private void setBook(ResultSet book) {
        this.author = book;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() throws SQLException {
        if (this.id != null) {
            return this.id;
        }
        this.author.next();
        String theId = this.author.getString(1);
        Integer id = Integer.parseInt(theId);
        this.setId(id);
        return id;
    }

    public String getFn() {
        return this.fn;
    }

    public String getLn() {
        return this.ln;
    }

    public int getYearPublished() {
        return this.firstPublished;
    }

    public void addAuthor(Integer authorId) throws SQLException {
        AuthorshipSQLMaker authorshipTool = new AuthorshipSQLMaker();
        String linkingQuery = authorshipTool.createAuthorship(authorId, this.getId());
        //todo: move this pg stuff out of here
//        Database pg = this.getPg();
//        pg.operateUpdate(linkingQuery);
//        BookSQLMaker bookTool = new BookSQLMaker();
//        ResultSet linked = pg.operate(bookTool.getBooksForAuthor(authorId));
//        this.setBook(linked);

    }

    public String toString() {
        return String.format("Book {name: %s, authorId: %s, name: %s, rented: %s }",
                this.fn + this.ln, authorId, "temp", rented);
    }
}

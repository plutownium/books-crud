package com.example.bookscrud.sqlMaker;

import com.example.bookscrud.author.Author;
import com.example.bookscrud.book.Book;

public class BookSQLMaker {
    public String createBookTableIfNotExists()  {
        return String.format("CREATE TABLE IF NOT EXISTS Books " +
                "(ID                     SERIAL PRIMARY KEY," +
                " TITLE                  TEXT    NOT NULL, " +
                " YEAR                   INT     NOT NULL, " +
                " RENTED                 BOOLEAN NULL,  " +
                " AUTHOR_ID              INT     NOT NULL, " +
                " FOREIGN KEY (author_id) REFERENCES Authors(id)" +
                ");");
    }

    public String createBook(String title, int year)  {
        return String.format("INSERT INTO Books (title, year, rented) " +
                "VALUES ('%s', %s, false) RETURNING id;", title, year);
    }

    public String createBookFromObj(Book book) {
        return String.format("INSERT INTO Books (title, year, rented, author_id) " +
                "VALUES ('%s', '%s', '%s', '%s') RETURNING id;", book.getTitle(), book.getYear(), book.getRentedStatus(), book.getAuthorId());
    }


    public String getBooksForAuthor(int authorId)  {
        // Yes, "FROM Authorships"
        return String.format("SELECT * FROM Authorships WHERE author_id=%s;", authorId);
    }

    public String getBooksForAuthorWithBookDetails(int authorId) {
        return String.format("SELECT b.* " +
                "FROM Authorships a " +
                "JOIN Books b ON a.book_id = b.id " +
                "WHERE a.author_id = %s;", authorId);
    }

    public String count() {
        return "SELECT count(*) AS exact_count FROM Books;";
    }


    public String getAllBooks()  {
        return "SELECT * FROM Books";
    }


    public String rentBookToGuest(int bookId) {
        return String.format("UPDATE Books SET rented = true WHERE id = %s;", bookId);
    }



    public String updateBookTitle(Integer id, String title)  {
        return String.format("UPDATE Books SET title=%s WHERE id=%s;", title, id);
    }


    public String deleteBookByTitle(String title)  {
        return String.format("DELETE FROM Books WHERE title=%s;", title);
    }

    public String deleteAll() {
        return "TRUNCATE Books CASCADE;";
    }
}

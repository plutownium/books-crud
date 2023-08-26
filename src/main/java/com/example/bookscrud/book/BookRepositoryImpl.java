package com.example.bookscrud.book;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

//import org.hibernate.sql.results.spi.ResultsConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import com.example.bookscrud.db.Database;
import com.example.bookscrud.sqlMaker.BookSQLMaker;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private Database db;
    public BookRepositoryImpl() {

        Database started = new Database();
        started.connect();
        this.db = started;
    }

    public char foo() {
        return 'c';
    }

    public void createTableIfNotExists() {
        try {

            db.operate(new BookSQLMaker().deleteAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Autowired
    public void BookRepository(DataSource dataSource) {
    }


    public boolean emailExists(String email) {
        //
        return true;
    }

    public List<Book> getAll() {

        // Database db = new Database();
        // try {

        //     System.out.println("try connect --- in the getAll");
        //     // db.connect();
        // } catch(Exception e) {
        //     System.out.println("error!!!!!! with connecting");
        //     throw e;
        // }

        List<Book> currentBooks = new ArrayList<Book>();
        String sqlForBook = new BookSQLMaker().getAllBooks();
        ResultSet resultSet;

        try {
            System.out.println(sqlForBook);
            resultSet = db.operate(sqlForBook);
            // boolean x = resultSet.next();
            // System.out.println(x);
            while (resultSet.next()) {
                Long id = Long.valueOf(resultSet.getInt("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double gpa = resultSet.getDouble("gpa");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                Book retrieved = new Book(this.db, name, 1, 2);
                currentBooks.add(retrieved);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return currentBooks;
    }

    public Book create(Book premadeBook) {
        System.out.println("in the create. Here is Book");
        System.out.println(premadeBook);


        Book newBook;
        String sqlForBook = new BookSQLMaker().createBook("foo", 1999);
        ResultSet resultSet;
        try {
            System.out.println(sqlForBook);
            resultSet = db.operate(sqlForBook);
            boolean x = resultSet.next();
            System.out.println(x);
            while (x) {
                Long id = Long.valueOf(resultSet.getInt("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double gpa = resultSet.getDouble("gpa");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                newBook = new Book(this.db, name,100, 101);
                // Book newBook = new Book(3L, "hatttt", "cattttt", LocalDate.of(1111, Month.MARCH, 3), 5);
                return newBook;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return premadeBook;


    }

    public String delete(int id) {
        String sqlForBook = new BookSQLMaker().deleteBookByTitle("temp");

        try {
            System.out.println(sqlForBook);
            db.operate(sqlForBook);
            return "Success";
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Fail";
    }

    public void destroyAll() {
        String sql = new BookSQLMaker().deleteAll();

        try {
            System.out.println(sql);
            db.operate(sql);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
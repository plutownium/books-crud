package com.example.bookscrud.book;



// import hotjar.demo.Book.Book;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

//import org.hibernate.sql.results.spi.ResultsConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;


public class BookRepositoryImpl implements BookRepository {

    private String mode;
    private Database db;
    public BookRepositoryImpl(String mode) {
        this.mode = mode;
        Database started = new Database(mode);
        started.connect();
        this.db = started;
    }

    public char foo() {
        return 'c';
    }

    public void createTableIfNotExists() {
        try {

            db.createTableIfNotExists("Books");
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
        String sqlForBook = BookSQLMaker.makeGetAllBooksSQL();
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
                Book retrieved = new Book(id, name, email, dob, age);
                currentBooks.add(retrieved);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return currentBooks;
    }

    public Book create(Book Book) {
        System.out.println("in the create. Here is Book");
        System.out.println(Book);
        // Database db = new Database();
        // try {

        //     System.out.println("try connect");
        //     db.connect();
        // } catch(Exception e) {
        //     System.out.println("error!!!!!! with connecting");
        //     throw e;
        // }

        Book newBook;
        String sqlForBook = BookSQLMaker.makeCreateBookSQL(Book);
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
                newBook = new Book(id, name, email, dob, age);
                // Book newBook = new Book(3L, "hatttt", "cattttt", LocalDate.of(1111, Month.MARCH, 3), 5);
                return newBook;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        // return if failed
        Book BookAgain = new Book(3L, "hat", "cat", LocalDate.of(2000, Month.MARCH, 3), 5);
        return BookAgain;
        // return new Book()
    }

    public String delete(int id) {
        // Database db = new Database();
        // try {

        //     System.out.println("try connect --- in the getAll");
        //     db.connect();
        // } catch(Exception e) {
        //     System.out.println("error!!!!!! with connecting");
        //     throw e;
        // }

        String sqlForBook = BookSQLMaker.makeDeleteBookSQL(id);

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
        // Database db = new Database();
        String sql = BookSQLMaker.destroyAll();

        try {
            System.out.println(sql);
            db.operate(sql);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
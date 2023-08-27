package com.example.bookscrud.author;

import com.example.bookscrud.db.Database;
import com.example.bookscrud.sqlMaker.BookSQLMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private Database db;
    public AuthorRepositoryImpl() {

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

    public List<Author> getAll() {

        // Database db = new Database();
        // try {

        //     System.out.println("try connect --- in the getAll");
        //     // db.connect();
        // } catch(Exception e) {
        //     System.out.println("error!!!!!! with connecting");
        //     throw e;
        // }

        List<Author> currentBooks = new ArrayList<Author>();
        String sqlForBook = new BookSQLMaker().getAllBooks();
        ResultSet resultSet;

        try {
            System.out.println(sqlForBook);
            resultSet = db.operate(sqlForBook);
            // boolean x = resultSet.next();
            // System.out.println(x);
            while (resultSet.next()) {
                long id = (long) resultSet.getInt("id");
                String fn = resultSet.getString("firstname");
                String ln = resultSet.getString("lastname");
                long published = (long) resultSet.getInt("published");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double gpa = resultSet.getDouble("gpa");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                Author retrieved = new Author(Math.toIntExact(id), fn, ln, Math.toIntExact(published));
                currentBooks.add(retrieved);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return currentBooks;
    }

    public Author create(Author premadeBook) {
        System.out.println("in the create. Here is Book");
        System.out.println(premadeBook);


        Author newBook;
        String sqlForBook = new BookSQLMaker().createBook("foo", 1999);
        ResultSet resultSet;
        try {
            System.out.println(sqlForBook);
            resultSet = db.operate(sqlForBook);
            boolean x = resultSet.next();
            System.out.println(x);
            while (x) {
                long id = (long) resultSet.getInt("id");
                String fn = resultSet.getString("firstname");
                String ln = resultSet.getString("lastname");
                long published = (long) resultSet.getInt("published");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double gpa = resultSet.getDouble("gpa");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                newBook = new Author(Math.toIntExact(id), fn, ln, Math.toIntExact(published));
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
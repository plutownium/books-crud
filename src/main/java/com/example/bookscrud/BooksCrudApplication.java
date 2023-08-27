package com.example.bookscrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.bookscrud.util.Seeds;
import com.example.bookscrud.util.Seeder;
import com.example.bookscrud.author.Author;
import com.example.bookscrud.book.Book;
import com.example.bookscrud.db.Database;
import com.example.bookscrud.extractor.*;
import com.example.bookscrud.sqlMaker.*;



@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class BooksCrudApplication {

	// todo: put in startup seed of 6 authors and 10 books and 3 guests
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BooksCrudApplication.class, args);
		Database db = new Database();
		db.connect();
		Seeder seeder = new Seeder(db);
		int NUM_OF_SEED_AUTHORS = 6;
		int NUM_OF_SEED_BOOKS = 10;
		int NUM_OF_SEED_GUESTS = 3;
		// seed authors etc. if not yet present
		// - get all authors
		AuthorSQLMaker authorSQL = new AuthorSQLMaker();
		ResultSet rs1 = db.operate(authorSQL.getAllAuthors());
		Author[] authors = AuthorExtractor.extract(rs1);
		// - put authors in if none present
		if (authors.length >= NUM_OF_SEED_AUTHORS) {
			// do nothing
		} else {

			List<Author> seedAuthors = new Seeds().getSeedAuthors();
			for (final Author authorToWrite : seedAuthors) {
				seeder.seedAuthor(authorToWrite, authorSQL);
			}

		}
		// - get all books
		BookSQLMaker bookSQL = new BookSQLMaker();
		ResultSet rs2 = db.operate(bookSQL.getAllBooks());
		Book[] books = BookExtractor.extract(rs2);
		// - put books in if none present
		if (books.length >= NUM_OF_SEED_BOOKS) {
			// do nothing
		} else {

			List<Book> seedBooks = new Seeds().getSeedBooks();
			for (final Book bookToWrite : seedBooks) {
				seeder.seedBook(bookToWrite, bookSQL);
			}

		}
		// - get all guests
		// - put guests in if none present
	}

	@GetMapping(value = "/j")
	public List<String> hello() {
		return List.of("Hi", "Jared", "You are hired");
	}
}

package com.example.bookscrud;


import com.example.bookscrud.util.SeedAuthors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Iterator;

import com.example.bookscrud.author.Author;
import com.example.bookscrud.db.Database;
import com.example.bookscrud.extractor.AuthorExtractor;
import com.example.bookscrud.sqlMaker.AuthorSQLMaker;
import com.example.bookscrud.util.Seeder;


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
		// seed authors etc if not yet present
		// - get all authors
		AuthorSQLMaker authorSQL = new AuthorSQLMaker();
		ResultSet thing = db.operate(authorSQL.getAllAuthors());
		Author[] authors = AuthorExtractor.extract(thing);
		// - put authors in if none present
		if (authors.length >= NUM_OF_SEED_AUTHORS) {
			// do nothing
		} else {
			List<Author> seedAuthors = new SeedAuthors().getSeedAuthors();
			for (final Author author : seedAuthors) {
				String addAuthorCmd = authorSQL.createAuthorFromObj(author);
				db.operate(addAuthorCmd);
			}

		}
		// - get all books
		// - put books in if none present
		// - get all guests
		// - put guests in if none present
	}

	@GetMapping(value = "/j")
	public List<String> hello() {
		return List.of("Hi", "Jared", "You are hired");
	}
}

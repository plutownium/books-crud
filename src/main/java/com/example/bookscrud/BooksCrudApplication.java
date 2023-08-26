package com.example.bookscrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BooksCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksCrudApplication.class, args);
	}

}

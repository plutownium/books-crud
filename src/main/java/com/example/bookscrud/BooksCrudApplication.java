package com.example.bookscrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class BooksCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksCrudApplication.class, args);
	}

	@GetMapping(value = "/j")
	public List<String> hello() {
		return List.of("Hi", "Jared", "You are hired");
	}
}

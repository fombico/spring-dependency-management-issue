package com.example.foo.app;

import com.example.foo.core.Person;
import jakarta.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	void setup() {
		var person = new Person("Fred");
		System.out.println(person.getName());
	}
}

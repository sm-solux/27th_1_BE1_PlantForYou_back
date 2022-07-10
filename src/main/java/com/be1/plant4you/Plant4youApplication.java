package com.be1.plant4you;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Plant4youApplication {

	public static void main(String[] args) {
		SpringApplication.run(Plant4youApplication.class, args);
	}
}

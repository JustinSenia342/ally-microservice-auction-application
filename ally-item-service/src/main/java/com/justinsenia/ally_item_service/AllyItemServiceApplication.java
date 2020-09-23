package com.justinsenia.ally_item_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AllyItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllyItemServiceApplication.class, args);
	}

}

package com.justinsenia.ally_user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AllyUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllyUserServiceApplication.class, args);
	}

}

package com.justinsenia.ally_auction_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AllyAuctionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllyAuctionServiceApplication.class, args);
	}

}

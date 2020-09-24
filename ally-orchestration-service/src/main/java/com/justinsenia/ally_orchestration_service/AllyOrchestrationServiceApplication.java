package com.justinsenia.ally_orchestration_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.justinsenia.ally_orchestration_service")
@EnableDiscoveryClient
public class AllyOrchestrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllyOrchestrationServiceApplication.class, args);
	}

}

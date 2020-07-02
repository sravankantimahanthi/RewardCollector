package com.demo.rewardapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.demo.rewardapp.repository")
public class RewardCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardCollectorApplication.class, args);
	}

}

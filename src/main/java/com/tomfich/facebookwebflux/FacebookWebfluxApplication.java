package com.tomfich.facebookwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(considerNestedRepositories = true)
public class FacebookWebfluxApplication {
	public static void main(String[] args) {
		SpringApplication.run(FacebookWebfluxApplication.class, args);
	}

}

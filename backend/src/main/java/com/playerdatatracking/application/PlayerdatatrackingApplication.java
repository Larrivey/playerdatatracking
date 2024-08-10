package com.playerdatatracking.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.playerdatatracking")
@EnableJpaRepositories(basePackages = "com.playerdatatracking.repositories")
@EntityScan(basePackages = "com.playerdatatracking.entities")
@SpringBootApplication
public class PlayerdatatrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerdatatrackingApplication.class, args);
	}
}

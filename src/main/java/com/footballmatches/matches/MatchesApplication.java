package com.footballmatches.matches;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@ComponentScan("com.footballmatches.matches")
@EnableMethodSecurity //This annotation make sure your role will get apply to API
public class MatchesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchesApplication.class, args);
	}
}

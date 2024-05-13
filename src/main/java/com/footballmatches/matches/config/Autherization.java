package com.footballmatches.matches.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Autherization implements WebMvcConfigurer {
	/**/	
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Specify allowed origins (e.g., "*", "http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH") // Specify allowed HTTP methods
                .allowedHeaders("*"); // Specify allowed headers
     }
}

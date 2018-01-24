package com.engage.tech.backendcodingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackages = "com.engage.tech.backendcodingtest")
@EnableJpaRepositories(basePackages = "com.engage.tech.backendcodingtest.repo")
@EntityScan(basePackages = "com.engage.tech.backendcodingtest.domain")
public class BackendCodingTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCodingTestApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/expenses").allowedOrigins("http://localhost:8080");
			}
		};
	}
}

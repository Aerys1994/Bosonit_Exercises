package com.Bosonit.Block7CrudValidation;

import com.Bosonit.Block7CrudValidation.controllers.dto.PersonaInputDto;
import com.Bosonit.Block7CrudValidation.domain.Persona;
import com.Bosonit.Block7CrudValidation.repository.PersonaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

@SpringBootApplication
public class Block11CorsApplication {

	@Autowired
	PersonaRepository personaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Block11CorsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/addperson").allowedOrigins("https://codepen.io");
				registry.addMapping("/getall").allowedOrigins("https://codepen.io");
			}
		};
	}

	@PostConstruct
	public void populateDb() {
		try {
			personaRepository.save(new Persona(new PersonaInputDto(1,"123456", "dfgdfg", "Andreu",
					"Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
					true, new Date(), "lolo.jpg", new Date())));
			personaRepository.save(new Persona(new PersonaInputDto(2,"123456", "dfgdfg", "Tim",
					"Valles", "aoae@aa.es", "aoaoeoa@asdf.es", "Palma",
					true, new Date(), "lolo.jpg", new Date())));
			personaRepository.save(new Persona(new PersonaInputDto(3,"123456", "dfgdfg", "Rosa",
					"Gutierrez", "aoae@aa.es", "aoaoeoa@asdf.es", "Palma",
					true, new Date(), "lolo.jpg", new Date())));
			personaRepository.save(new Persona(new PersonaInputDto(4,"123456", "dfgdfg", "Adri",
					"Ladariu", "aoae@aa.es", "aoaoeoa@asdf.es", "Palma",
					true, new Date(), "lolo.jpg", new Date())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

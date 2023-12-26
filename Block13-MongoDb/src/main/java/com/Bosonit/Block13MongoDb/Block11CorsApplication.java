package com.Bosonit.Block13MongoDb;

import com.Bosonit.Block13MongoDb.controllers.dto.PersonaInputDto;
import com.Bosonit.Block13MongoDb.domain.Persona;
import com.Bosonit.Block13MongoDb.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;


@SpringBootApplication
public class Block11CorsApplication implements CommandLineRunner {

	@Autowired
	PersonaRepository personaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Block11CorsApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {

		personaRepository.deleteAll();

		personaRepository.save(new Persona(new PersonaInputDto("123456", "dfgdfg", "Andreu",
				"Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
				true, new Date(), "lolo.jpg", new Date())));
		personaRepository.save(new Persona(new PersonaInputDto("123456", "dfgdfg", "Tim",
				"Valles", "aoae@aa.es", "aoaoeoa@asdf.es", "Palma",
				true, new Date(), "lolo.jpg", new Date())));
		personaRepository.save(new Persona(new PersonaInputDto("123456", "dfgdfg", "Rosa",
				"Gutierrez", "aoae@aa.es", "aoaoeoa@asdf.es", "Palma",
				true, new Date(), "lolo.jpg", new Date())));
		personaRepository.save(new Persona(new PersonaInputDto("123456", "dfgdfg", "Adri",
				"Ladariu", "aoae@aa.es", "aoaoeoa@asdf.es", "Palma",
				true, new Date(), "lolo.jpg", new Date())));

	}
}

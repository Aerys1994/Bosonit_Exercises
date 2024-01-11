package com.Bosonit.block5profiles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Block5ProfilesApplication implements CommandLineRunner{

	private final Variables variables;

	public static void main(String[] args) {
		SpringApplication.run(Block5ProfilesApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		log.info("Current profile is {}, current url is {}", variables.getActiveProfile(), variables.getDbUrl() );
	}
}

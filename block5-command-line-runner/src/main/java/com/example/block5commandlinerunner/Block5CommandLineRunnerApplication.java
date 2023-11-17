package com.example.block5commandlinerunner;

import Components.AppConfig;
import Components.PostConstructor;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AppConfig.class, PostConstructor.class})

public class Block5CommandLineRunnerApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(Block5CommandLineRunnerApplication.class, args);
	}
}

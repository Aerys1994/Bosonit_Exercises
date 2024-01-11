package Components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner secundaria() {
        return args -> System.out.println("Hola desde clase secundaria");
    }

    @Bean
    public CommandLineRunner tercera(String p) {
        return args -> System.out.println(p);
    }

}

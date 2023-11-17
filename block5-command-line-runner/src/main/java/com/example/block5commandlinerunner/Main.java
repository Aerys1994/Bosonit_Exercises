package com.example.block5commandlinerunner;
import Components.AppConfig;
import Components.PostConstructor;
import Components.PostConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
public class Main {


    @SpringBootApplication
    @Import({AppConfig.class, PostConstructor.class})
    public class MainApplication {

        public static void main(String[] args) {
            SpringApplication.run(MainApplication.class, args);
        }
    }
}

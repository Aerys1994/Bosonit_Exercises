package com.Bosonit.block6personcontrollers;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public CiudadService ciudadService() {
        return new CiudadServiceImpl();
    }
}

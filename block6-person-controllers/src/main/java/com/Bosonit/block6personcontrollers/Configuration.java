package com.Bosonit.block6personcontrollers;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public CiudadService ciudadService() {
        return new CiudadServiceImpl();
    }

    @Bean("bean1")
    public Person personBean1() {
        Person person = new Person();
        person.setNombre("bean1");
        return person;
    }

    @Bean("bean2")
    public Person personBean2() {
        Person person = new Person();
        person.setNombre("bean2");
        return person;
    }

    @Bean("bean3")
    public Person personBean3() {
        Person person = new Person();
        person.setNombre("bean3");
        return person;
    }
}

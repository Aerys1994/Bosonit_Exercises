package com.Bosonit.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    private final PersonService personService;


    public Controlador2(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getPerson")
    public Person getPerson(
            @RequestHeader String nombre,
            @RequestHeader String poblacion,
            @RequestHeader int edad
    ) {
        Person person = new Person();
        person.setNombre(nombre);
        person.setPoblacion(poblacion);
        person.setEdad(edad);
        return personService.ageTimesTwo(person);
    }
}

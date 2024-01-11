package com.Bosonit.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    private final PersonService personService;
    private final CiudadService ciudadService;


    public Controlador2(PersonService personService, CiudadService ciudadService) {
        this.personService = personService;
        this.ciudadService = ciudadService;
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

    @GetMapping("/getCiudades")
    public List<Ciudad> getCiudades() {
        return ciudadService.obtenerCiudades();
    }
}

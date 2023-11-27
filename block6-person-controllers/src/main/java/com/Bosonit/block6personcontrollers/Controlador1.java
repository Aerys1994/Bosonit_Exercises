package com.Bosonit.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    private final PersonService personService;


    public Controlador1(PersonService personService)
    {
        this.personService = personService;
    }

    @GetMapping("/addPerson")
    public Person addPerson(
            @RequestHeader String nombre,
            @RequestHeader String poblacion,
            @RequestHeader int edad
    ) {
        return personService.createPerson(nombre, poblacion, edad);
    }
}

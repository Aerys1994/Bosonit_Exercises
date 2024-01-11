package com.Bosonit.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    private final PersonService personService;
    private final CiudadService ciudadService;


    public Controlador1(PersonService personService, CiudadService ciudadService)
    {
        this.personService = personService;
        this.ciudadService = ciudadService;
    }

    @GetMapping("/addPerson")
    public Person addPerson(
            @RequestHeader String nombre,
            @RequestHeader String poblacion,
            @RequestHeader int edad
    ) {
        return personService.createPerson(nombre, poblacion, edad);
    }

    @PostMapping("/addCiudad")
    public void addCiudad(@RequestBody Ciudad ciudad) {
        ciudadService.agregarCiudad(ciudad);
    }
}

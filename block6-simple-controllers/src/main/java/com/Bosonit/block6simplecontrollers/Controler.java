package com.Bosonit.block6simplecontrollers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/micontrolador")
public class Controler {

    @GetMapping(value="/hello/{name}")

    public String hello(@PathVariable String name)
    {
        return "Hola " + name;
    }

    @PostMapping(value="/useradd")
    public Person addUser(@RequestBody Person persona)
    {
        persona.setEdad(persona.getEdad() + 1);
        return persona;
    }
}

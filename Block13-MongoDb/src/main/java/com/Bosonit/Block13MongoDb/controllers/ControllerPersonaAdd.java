package com.Bosonit.Block13MongoDb.controllers;

import com.Bosonit.Block13MongoDb.application.PersonaService;
import com.Bosonit.Block13MongoDb.controllers.dto.PersonaInputDto;
import com.Bosonit.Block13MongoDb.controllers.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class ControllerPersonaAdd {

    @Autowired
    PersonaService personaService;

    @PostMapping("/addperson")
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody
                                                           PersonaInputDto persona) throws Exception {

        System.out.println("Received request to add persona: " + persona);
        URI location = URI.create("/persona");
        return ResponseEntity.created(location)
                .body(personaService.addPersona(persona));
    }
}

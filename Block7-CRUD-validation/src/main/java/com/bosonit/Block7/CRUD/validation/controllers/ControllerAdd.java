package com.bosonit.Block7.CRUD.validation.controllers;

import com.bosonit.Block7.CRUD.validation.application.PersonaService;
import com.bosonit.Block7.CRUD.validation.controllers.dto.PersonaInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class ControllerAdd {

    @Autowired
    PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody
                                                       PersonaInputDto persona) throws Exception {
        URI location = URI.create("/persona");
        return ResponseEntity.created(location)
                .body(personaService.addPersona(persona));
    }
}
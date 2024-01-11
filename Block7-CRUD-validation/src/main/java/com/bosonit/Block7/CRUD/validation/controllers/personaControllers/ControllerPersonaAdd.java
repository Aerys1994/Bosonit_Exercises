package com.bosonit.Block7.CRUD.validation.controllers.personaControllers;

import com.bosonit.Block7.CRUD.validation.application.PersonaServiceImpl;
import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class ControllerPersonaAdd {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody
                                                       PersonaInputDto persona) throws Exception {
        URI location = URI.create("/persona");
        return ResponseEntity.created(location)
                .body(personaServiceImpl.addPersona(persona));
    }
}
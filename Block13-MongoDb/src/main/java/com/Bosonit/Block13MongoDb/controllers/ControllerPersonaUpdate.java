package com.Bosonit.Block13MongoDb.controllers;


import com.Bosonit.Block13MongoDb.application.PersonaService;
import com.Bosonit.Block13MongoDb.controllers.dto.PersonaInputDto;
import com.Bosonit.Block13MongoDb.controllers.dto.PersonaOutputDto;
import com.Bosonit.Block13MongoDb.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class ControllerPersonaUpdate {

    @Autowired
    PersonaService personaService;
    @PutMapping
    public ResponseEntity<PersonaOutputDto> updatePersona(
            @PathVariable String idPersona,
            @RequestBody(required = false) PersonaInputDto personaInputDto) throws Exception {

        return ResponseEntity.ok(personaService.updatePersona(idPersona, personaInputDto));
    }


    @DeleteMapping("/{idPersona}")
    public void deletePersonaById(@PathVariable String idPersona) {
        personaService.deletePersona(idPersona);
    }
}

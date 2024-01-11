package com.Bosonit.Block11Cors.controllers;


import com.Bosonit.Block11Cors.application.PersonaService;
import com.Bosonit.Block11Cors.controllers.dto.PersonaInputDto;
import com.Bosonit.Block11Cors.controllers.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class ControllerPersonaUpdate {

    @Autowired
    PersonaService personaService;
    @PutMapping
    public ResponseEntity<PersonaOutputDto> updatePersona(@RequestBody PersonaInputDto persona) {

            personaService.getPersonaById(persona.getId());
        try {
            return  ResponseEntity.ok().body(personaService.addPersona(persona));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

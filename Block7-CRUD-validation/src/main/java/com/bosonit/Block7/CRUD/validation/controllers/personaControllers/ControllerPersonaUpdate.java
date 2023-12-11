package com.bosonit.Block7.CRUD.validation.controllers.personaControllers;

import com.bosonit.Block7.CRUD.validation.application.PersonaService;
import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaOutputDto;
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
        try {
            personaService.getPersonaById(persona.getId(), false, false);
            return  ResponseEntity.ok().body(personaService.addPersona(persona));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}

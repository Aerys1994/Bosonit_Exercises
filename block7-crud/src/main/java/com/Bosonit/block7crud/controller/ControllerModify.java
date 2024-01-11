package com.Bosonit.block7crud.controller;

import com.Bosonit.block7crud.application.PersonaService;
import com.Bosonit.block7crud.application.PersonaServiceImpl;
import com.Bosonit.block7crud.controller.dto.PersonaInputDto;
import com.Bosonit.block7crud.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class ControllerModify {

    @Autowired
    PersonaService personaService;
    @PutMapping
    public ResponseEntity<PersonaOutputDto> updateStudent(@RequestBody PersonaInputDto persona) {
        try {
            personaService.getPersonaById(persona.getId());
            return  ResponseEntity.ok().body(personaService.addPersona(persona));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

package com.Bosonit.block7crud.controller;

import com.Bosonit.block7crud.application.PersonaService;
import com.Bosonit.block7crud.application.PersonaServiceImpl;
import com.Bosonit.block7crud.controller.dto.PersonaInputDto;
import com.Bosonit.block7crud.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class ControllerAdd {

    @Autowired
    PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody
                                                      PersonaInputDto persona) {
        URI location = URI.create("/persona");
        return ResponseEntity.created(location)
                .body(personaService.addPersona(persona));
    }
}

package com.bosonit.Block7.CRUD.validation.controllers;

import com.bosonit.Block7.CRUD.validation.application.PersonaService;
import com.bosonit.Block7.CRUD.validation.controllers.dto.PersonaOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class ControllerGet {

    PersonaService personaService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PersonaOutputDto>> getPersonasPorUsuario(@PathVariable String usuario) {
        try {
            List<PersonaOutputDto> personas = personaService.getPersonaByUsuario(usuario);
            return ResponseEntity.ok().body(personas);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersona(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize) {

        return personaService.getAllPersona(pageNumber, pageSize);
    }
}

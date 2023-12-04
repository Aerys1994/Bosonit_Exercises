package com.Bosonit.block7crud.controller;

import com.Bosonit.block7crud.application.PersonaService;
import com.Bosonit.block7crud.application.PersonaServiceImpl;
import com.Bosonit.block7crud.controller.dto.PersonaOutputDto;
import com.Bosonit.block7crud.domain.Persona;
import com.Bosonit.block7crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class ControllerGet {

    @Autowired
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
    public ResponseEntity<List<PersonaOutputDto>> getPersonasPorNombre(@PathVariable String nombre) {
        try {
            List<PersonaOutputDto> personas = personaService.getPersonaByName(nombre);
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

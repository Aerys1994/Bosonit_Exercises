package com.Bosonit.Block11Cors.controllers;

import com.Bosonit.Block11Cors.application.PersonaService;
import com.Bosonit.Block11Cors.controllers.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class ControllerPersonaGet {

    @Autowired
    PersonaService personaService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(
            @PathVariable int id) {

            return ResponseEntity.ok().body(personaService.getPersonaById(
                    id));

    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PersonaOutputDto>> getPersonasPorUsuario(
            @PathVariable String usuario) {

        List<PersonaOutputDto> personas = personaService.getPersonaByUsuario(
                    usuario);
        return ResponseEntity.ok().body(personas);

    }

    @CrossOrigin(origins = "https://cdpn.io")
    @GetMapping({"/getall"})
    public Iterable<PersonaOutputDto> getAllPersona(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize
            ) {

        return personaService.getAllPersona(pageNumber, pageSize);
    }

}

package com.Bosonit.block7crud.controller;

import com.Bosonit.block7crud.application.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class ControllerDelete {

    @Autowired
    PersonaServiceImpl personaService;

    @DeleteMapping
    public ResponseEntity<String> deletePersonaById(@RequestParam int id) {
        try {
            personaService.deletePersonaByID(id);
            return ResponseEntity.ok().body("La persona: " +
                    personaService.getPersonaById(id) + " ha sido eliminada");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

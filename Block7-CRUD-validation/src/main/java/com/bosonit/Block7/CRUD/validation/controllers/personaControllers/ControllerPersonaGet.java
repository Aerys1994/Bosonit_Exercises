package com.bosonit.Block7.CRUD.validation.controllers.personaControllers;

import com.bosonit.Block7.CRUD.validation.application.PersonaServiceImpl;
import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class ControllerPersonaGet {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(
            @PathVariable int id,
            @RequestParam(defaultValue = "false") boolean includeStudent,
            @RequestParam(defaultValue = "false") boolean includeProfessor) {
        try {
            return ResponseEntity.ok().body(personaServiceImpl.getPersonaById(
                    id, includeStudent, includeProfessor));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PersonaOutputDto>> getPersonasPorUsuario(
            @PathVariable String usuario,
            @RequestParam(defaultValue = "false") boolean includeStudent,
            @RequestParam(defaultValue = "false") boolean includeProfessor) {
        try {
            List<PersonaOutputDto> personas = personaServiceImpl.getPersonaByUsuario(
                    usuario, includeStudent, includeProfessor);
            return ResponseEntity.ok().body(personas);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allPersona")
    public Iterable<PersonaOutputDto> getAllPersona(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize,
            @RequestParam(defaultValue = "false") boolean includeStudent,
            @RequestParam(defaultValue = "false") boolean includeProfessor) {

        return personaServiceImpl.getAllPersona(pageNumber, pageSize, includeStudent, includeProfessor);
    }
}

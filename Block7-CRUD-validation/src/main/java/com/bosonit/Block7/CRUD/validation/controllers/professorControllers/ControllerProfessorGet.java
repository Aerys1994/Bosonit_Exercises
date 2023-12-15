package com.bosonit.Block7.CRUD.validation.controllers.professorControllers;

import com.bosonit.Block7.CRUD.validation.application.ProfessorServiceImpl;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ControllerProfessorGet {

    @Autowired
    ProfessorServiceImpl professorService;

    @GetMapping("/{idProfessor}")
    public ResponseEntity<ProfessorOutputDto> getProfessorById(@PathVariable int idProfessor) {
        try {
            return ResponseEntity.ok().body(professorService.getProfessorById(idProfessor).professorToProfessorOutputDto());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<ProfessorOutputDto> getAllPersona(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize) {

        return professorService.getAllProfessor(pageNumber, pageSize);
    }
}

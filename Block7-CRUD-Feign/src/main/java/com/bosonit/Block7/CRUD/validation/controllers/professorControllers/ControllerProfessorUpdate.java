package com.bosonit.Block7.CRUD.validation.controllers.professorControllers;


import com.bosonit.Block7.CRUD.validation.application.ProfessorService;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professor")
public class ControllerProfessorUpdate {

    @Autowired
    ProfessorService professorService;
    @PutMapping
    public ResponseEntity<ProfessorOutputDto> updateStudent(@RequestBody ProfessorInputDto professor) {
        try {
            professorService.getProfessorById(professor.getId());
            return  ResponseEntity.ok().body(professorService.addProfessor(professor));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}

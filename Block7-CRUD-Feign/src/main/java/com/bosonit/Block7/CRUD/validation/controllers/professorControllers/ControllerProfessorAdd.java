package com.bosonit.Block7.CRUD.validation.controllers.professorControllers;

import com.bosonit.Block7.CRUD.validation.application.ProfessorServiceImpl;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/professor")
public class ControllerProfessorAdd {

    @Autowired
    ProfessorServiceImpl professorServiceImpl;

    @PostMapping
    public ResponseEntity<ProfessorOutputDto> addProfessor
            (@RequestBody ProfessorInputDto professor) throws Exception {

        try {
            URI location = URI.create("/professor");
            return ResponseEntity.created(location)
                    .body(professorServiceImpl.addProfessor(professor));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }
}

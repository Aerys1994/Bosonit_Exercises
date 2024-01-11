package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url = "http://localhost:8081", name = "myFeign")
@RequestMapping("/professor")
public interface FeignService {
    @GetMapping("/{idProfessor}")
    ProfessorOutputDto getProfessorById(@PathVariable("idProfesor") int id);
}

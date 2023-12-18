package com.bosonit.Block7.CRUD.validation.application;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorOutputDto;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "professor-service", url = "http://localhost:8081")
public interface ProfessorFeignClient {

    @GetMapping("/professor/{id}")
    ProfessorOutputDto getProfessor(@PathVariable int id);
}
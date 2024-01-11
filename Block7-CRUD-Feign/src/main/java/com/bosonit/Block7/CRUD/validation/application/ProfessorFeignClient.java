package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.domain.Professor;
import com.bosonit.Block7.CRUD.validation.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorOutputDto;


@Service
public class ProfessorFeignClient implements FeignService{

    @Autowired
    ProfessorRepository professorRepository;
    @Override
    public ProfessorOutputDto getProfessorById(int id) {
        Professor professor = professorRepository.findById(id).orElseThrow();
        return professor.professorToProfessorOutputDto();
    }
}
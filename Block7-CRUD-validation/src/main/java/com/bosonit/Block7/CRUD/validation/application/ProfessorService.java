package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorOutputDto;
import com.bosonit.Block7.CRUD.validation.domain.Professor;

import java.util.List;


public interface ProfessorService {

    Professor getProfessorById(int id);

    List<ProfessorOutputDto> getAllProfessor (int pageNumber, int pageSize);

    Professor createProfessor(int idProfessor);

    ProfessorOutputDto addProfessor (ProfessorInputDto professorInputDto) throws Exception;

    ProfessorOutputDto updateProfessor(int idProfessor, ProfessorInputDto professorInputDto) throws Exception;



}

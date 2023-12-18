package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaOutputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorOutputDto;

import java.util.List;

public interface PersonaService {

    PersonaOutputDto getPersonaById(int id, boolean includeStudent, boolean includeProfessor);

    List<PersonaOutputDto> getPersonaByUsuario(String usuario, boolean includeStudent, boolean includeProfessor);

    List<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize, boolean includeStudent, boolean includeProfessor);

    PersonaOutputDto addPersona (PersonaInputDto personaInputDto) throws Exception;

    PersonaOutputDto updatePersona(int idPersona, PersonaInputDto personaInputDto) throws Exception;


    ProfessorOutputDto getProfesorUsingRestTemplate(int idProfessor);

}

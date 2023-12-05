package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.PersonaInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {

    PersonaOutputDto getPersonaById(int id);

    List<PersonaOutputDto> getPersonaByUsuario(String usuario);

    List<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize);

    PersonaOutputDto addPersona (PersonaInputDto personaInputDto) throws Exception;

    PersonaOutputDto updatePersona(int idPersona, PersonaInputDto personaInputDto) throws Exception;




}

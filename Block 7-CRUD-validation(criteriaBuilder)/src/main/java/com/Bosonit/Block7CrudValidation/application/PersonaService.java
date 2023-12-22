package com.Bosonit.Block7CrudValidation.application;

import com.Bosonit.Block7CrudValidation.controllers.dto.PersonaInputDto;
import com.Bosonit.Block7CrudValidation.controllers.dto.PersonaOutputDto;

import java.util.HashMap;
import java.util.List;

public interface PersonaService {

    PersonaOutputDto getPersonaById(int idPersona);

    List<PersonaOutputDto> getPersonaByUsuario(String usuario);

    List<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize);

    PersonaOutputDto addPersona (PersonaInputDto personaInputDto) throws Exception;

    PersonaOutputDto updatePersona(int idPersona, PersonaInputDto personaInputDto) throws Exception;




}

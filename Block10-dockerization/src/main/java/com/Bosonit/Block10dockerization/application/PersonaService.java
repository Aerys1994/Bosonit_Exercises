package com.Bosonit.Block10dockerization.application;

import com.Bosonit.Block10dockerization.controllers.dto.PersonaInputDto;
import com.Bosonit.Block10dockerization.controllers.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {

    PersonaOutputDto getPersonaById(int id);

    List<PersonaOutputDto> getPersonaByUsuario(String usuario);

    List<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize);

    PersonaOutputDto addPersona (PersonaInputDto personaInputDto) throws Exception;

    PersonaOutputDto updatePersona(int idPersona, PersonaInputDto personaInputDto) throws Exception;



}

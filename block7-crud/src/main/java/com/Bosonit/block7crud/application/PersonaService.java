package com.Bosonit.block7crud.application;

import com.Bosonit.block7crud.controller.dto.PersonaInputDto;
import com.Bosonit.block7crud.controller.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {

    PersonaOutputDto addPersona(PersonaInputDto persona);

    PersonaOutputDto getPersonaById(int id);
    void deletePersonaByID(int id);
    List<PersonaOutputDto> getPersonaByName(String name);
    List<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize);
    PersonaOutputDto updatePersona(PersonaInputDto persona);
}

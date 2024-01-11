package com.Bosonit.Block13MongoDb.application;

import com.Bosonit.Block13MongoDb.controllers.dto.PersonaInputDto;
import com.Bosonit.Block13MongoDb.controllers.dto.PersonaOutputDto;

import java.util.Date;
import java.util.List;

public interface PersonaService {

    PersonaOutputDto getPersonaById(String idPersona);
    List<PersonaOutputDto> getPersonaByUsuario(String usuario);
    PersonaOutputDto addPersona(PersonaInputDto personaInputDto) throws Exception;
    PersonaOutputDto updatePersona(String idPersona, PersonaInputDto personaInputDto) throws Exception;
    List<PersonaOutputDto> getAllPersona(int pageNumber, int pageSize);
    void deletePersona(String idPersona);
    List<PersonaOutputDto> getPersonaByQuery(String usuario, String name, String surname, String companyEmail,
                                             String PersonalEmail, String city,
                                             Boolean active, Date createdDate,
                                             String imageUrl, Date terminationDate, String orderByField,
                                             boolean asc, int pageNumber, int pageSize);



}

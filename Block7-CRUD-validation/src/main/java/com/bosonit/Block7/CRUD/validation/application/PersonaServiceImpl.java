package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.PersonaInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.PersonaOutputDto;
import com.bosonit.Block7.CRUD.validation.domain.Persona;
import com.bosonit.Block7.CRUD.validation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDto getPersonaById(int id) {
        return personaRepository.findById(id).orElseThrow()
                .personaToPersonaOutputDto();
    }

    @Override
    public List<PersonaOutputDto> getPersonaByUsuario(String usuario) {
        return personaRepository.findByUsuario(usuario);
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) throws Exception
    {
        checkInputDto(personaInputDto);
        return personaRepository.save(new Persona(personaInputDto))
                .personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto updatePersona(int idPersona, PersonaInputDto personaInputDto) throws Exception
    {
        checkInputDto(personaInputDto);
        personaRepository.findById(personaInputDto.getId()).orElseThrow();
        return personaRepository.save(new Persona(personaInputDto))
                .personaToPersonaOutputDto();
    }

    private void checkInputDto(PersonaInputDto personaInputDto) throws Exception {

        if (isNulo(personaInputDto.getUsuario())) {
            throw new Exception("Usuario no puede ser nulo");
        } else if (personaInputDto.getUsuario().length() < 6 || personaInputDto.getUsuario().length() > 10 ) {
            throw new Exception("Usuario debe tener entre 6 y 10 caracteres");
        }

        if (isNulo(personaInputDto.getPassword())) {
            throw new Exception("Password no puede ser nulo");
        }

        if (isNulo(personaInputDto.getName())) {
            throw new Exception("Name no puede ser nulo");
        }
        if (isNulo(personaInputDto.getCompanyEmail())) {
            throw new Exception("CompanyEmail no puede ser nulo");
        }
        if (isNulo(personaInputDto.getPersonalEmail())) {
            throw new Exception("CompanyEmail no puede ser nulo");
        }
        if(isNulo(personaInputDto.getCity())) {
            throw new Exception("City no puede ser nulo");
        }
        if(isNulo(String.valueOf(personaInputDto.isActive()))) {
            throw new Exception("Active no puede ser nulo");
        }
        if(isNulo(personaInputDto.getCity())) {
            throw new Exception("City no puede ser nulo");
        }
        if(isNulo(String.valueOf(personaInputDto.getCreatedDate()))) {
            throw new Exception("CreatedDate no puede ser nulo");
        }

    }

    boolean isNulo(String str)
    {
        return str==null || str.isBlank();
    }

    @Override
    public List<PersonaOutputDto> getAllPersona(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList();
    }


}

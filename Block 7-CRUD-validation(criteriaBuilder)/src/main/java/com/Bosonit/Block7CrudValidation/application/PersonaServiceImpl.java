package com.Bosonit.Block7CrudValidation.application;

import com.Bosonit.Block7CrudValidation.controllers.dto.PersonaInputDto;
import com.Bosonit.Block7CrudValidation.controllers.dto.PersonaOutputDto;
import com.Bosonit.Block7CrudValidation.domain.Persona;
import com.Bosonit.Block7CrudValidation.exceptions.CustomEntityNotFoundException;
import com.Bosonit.Block7CrudValidation.exceptions.UnprocessableEntityException;
import com.Bosonit.Block7CrudValidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements com.Bosonit.Block7CrudValidation.application.PersonaService {

    @Autowired
    PersonaRepository personaRepository;


    @Override
    public PersonaOutputDto getPersonaById(int idPersona) {
        Persona persona = personaRepository.findById(idPersona).orElseThrow(
                () -> new CustomEntityNotFoundException("Persona not found with id: " + idPersona));

        PersonaOutputDto personaOutputDto = persona.personaToPersonaOutputDto();


        return personaOutputDto;

    }

    @Override
    public List<PersonaOutputDto> getPersonaByUsuario(String usuario) {
        Optional<Persona> personas = personaRepository.findByUsuario(usuario);

        return personas.stream()
                .map(persona -> {
                    PersonaOutputDto personaOutputDto = persona.personaToPersonaOutputDto();

                    return personaOutputDto;

                })
                .collect(Collectors.toList());
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) throws Exception {
        checkInputDto(personaInputDto);
        return personaRepository.save(new Persona(personaInputDto))
                .personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto updatePersona(int idPersona, PersonaInputDto personaInputDto) throws Exception {
        checkInputDto(personaInputDto);
        Persona persona = personaRepository.findById(personaInputDto.getIdPersona()).orElseThrow(()
                -> new CustomEntityNotFoundException("Persona no encontrada con id: " + idPersona));

        persona.setUsuario(personaInputDto.getUsuario());
        persona.setPassword(personaInputDto.getPassword());
        persona.setName(personaInputDto.getName());
        persona.setSurname(personaInputDto.getSurname());
        persona.setCompanyEmail(personaInputDto.getCompanyEmail());
        persona.setPersonalEmail(personaInputDto.getPersonalEmail());
        persona.setCity(personaInputDto.getCity());
        persona.setActive(personaInputDto.isActive());
        persona.setCreatedDate(personaInputDto.getCreatedDate());
        persona.setImageUrl(personaInputDto.getImageUrl());
        persona.setTerminationDate(personaInputDto.getTerminationDate());

        return persona.personaToPersonaOutputDto();
    }


    private void checkInputDto(PersonaInputDto personaInputDto) {

        if (isNulo(personaInputDto.getUsuario())) {
            throw new UnprocessableEntityException("Usuario no puede ser nulo");
        } else if (personaInputDto.getUsuario().length() < 6 || personaInputDto.getUsuario().length() > 10) {
            throw new UnprocessableEntityException("Usuario debe tener entre 6 y 10 caracteres");
        }

        if (isNulo(personaInputDto.getPassword())) {
            throw new UnprocessableEntityException("Password no puede ser nulo");
        }

        if (isNulo(personaInputDto.getName())) {
            throw new UnprocessableEntityException("Name no puede ser nulo");
        }
        if (isNulo(personaInputDto.getCompanyEmail())) {
            throw new UnprocessableEntityException("CompanyEmail no puede ser nulo");
        }
        if (isNulo(personaInputDto.getPersonalEmail())) {
            throw new UnprocessableEntityException("CompanyEmail no puede ser nulo");
        }
        if (isNulo(personaInputDto.getCity())) {
            throw new UnprocessableEntityException("City no puede ser nulo");
        }
        if (isNulo(String.valueOf(personaInputDto.isActive()))) {
            throw new UnprocessableEntityException("Active no puede ser nulo");
        }
        if (isNulo(personaInputDto.getCity())) {
            throw new UnprocessableEntityException("City no puede ser nulo");
        }
        if (isNulo(String.valueOf(personaInputDto.getCreatedDate()))) {
            throw new UnprocessableEntityException("CreatedDate no puede ser nulo");
        }

    }

    boolean isNulo(String str) {
        return str == null || str.isBlank();
    }

    @Override
    public List<PersonaOutputDto> getAllPersona(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(persona -> {
                    PersonaOutputDto personaOutputDto = persona.personaToPersonaOutputDto();
                    return personaOutputDto;
                })
                .toList();
    }

}
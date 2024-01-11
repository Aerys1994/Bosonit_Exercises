package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaOutputDto;
import com.bosonit.Block7.CRUD.validation.domain.Persona;
import com.bosonit.Block7.CRUD.validation.exceptions.CustomEntityNotFoundException;
import com.bosonit.Block7.CRUD.validation.exceptions.UnprocessableEntityException;
import com.bosonit.Block7.CRUD.validation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PersonaOutputDto getPersonaById(int id, boolean includeStudent, boolean includeProfessor) {
        Persona persona = personaRepository.findById(id).orElseThrow(
                () -> new CustomEntityNotFoundException("Persona no encontrada con ID: " + id));

        PersonaOutputDto personaOutputDto = persona.personaToPersonaOutputDto();

        if (includeStudent && persona.getStudent() != null) {
            personaOutputDto.setStudent(persona.getStudent());
        }
        if (includeProfessor && persona.getProfessor() != null) {
            personaOutputDto.setProfessor(persona.getProfessor());
        }

        return personaOutputDto;

    }

    @Override
    public List<PersonaOutputDto> getPersonaByUsuario(String usuario, boolean includeStudent, boolean includeProfessor) {
        List<Persona> personas = personaRepository.findByUsuario(usuario);

        return personas.stream()
                .map(persona -> {
                    PersonaOutputDto personaOutputDto = persona.personaToPersonaOutputDto();

                    if (includeStudent && persona.getStudent() != null) {
                        personaOutputDto.setStudent(persona.getStudent());
                    }
                    if (includeProfessor && persona.getProfessor() != null) {
                        personaOutputDto.setProfessor(persona.getProfessor());
                    }

                    return personaOutputDto;

                })
                .collect(Collectors.toList());
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
        personaRepository.findById(personaInputDto.getId()).orElseThrow(() -> new CustomEntityNotFoundException("Persona no encontrada con ID"));
        return personaRepository.save(new Persona(personaInputDto))
                .personaToPersonaOutputDto();
    }

    private void checkInputDto(PersonaInputDto personaInputDto) throws Exception {

        if (isNulo(personaInputDto.getUsuario())) {
            throw new Exception("Usuario no puede ser nulo");
        } else if (personaInputDto.getUsuario().length() < 6 || personaInputDto.getUsuario().length() > 10 ) {
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
        if(isNulo(personaInputDto.getCity())) {
            throw new UnprocessableEntityException("City no puede ser nulo");
        }
        if(isNulo(String.valueOf(personaInputDto.isActive()))) {
            throw new UnprocessableEntityException("Active no puede ser nulo");
        }
        if(isNulo(personaInputDto.getCity())) {
            throw new UnprocessableEntityException("City no puede ser nulo");
        }
        if(isNulo(String.valueOf(personaInputDto.getCreatedDate()))) {
            throw new UnprocessableEntityException("CreatedDate no puede ser nulo");
        }

    }

    boolean isNulo(String str)
    {
        return str==null || str.isBlank();
    }

    @Override
    public List<PersonaOutputDto> getAllPersona(int pageNumber, int pageSize, boolean includeStudent, boolean includeProfessor) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(persona -> {
                    PersonaOutputDto personaOutputDto = persona.personaToPersonaOutputDto();

                    if (includeStudent && persona.getStudent() != null) {
                        personaOutputDto.setStudent(persona.getStudent());
                    }

                    if (includeProfessor && persona.getProfessor() != null) {
                        personaOutputDto.setProfessor(persona.getProfessor());
                    }

                    return personaOutputDto;
                })
                .toList();
    }


}

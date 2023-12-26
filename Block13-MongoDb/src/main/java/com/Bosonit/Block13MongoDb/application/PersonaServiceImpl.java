package com.Bosonit.Block13MongoDb.application;

import com.Bosonit.Block13MongoDb.controllers.dto.PersonaInputDto;
import com.Bosonit.Block13MongoDb.controllers.dto.PersonaOutputDto;
import com.Bosonit.Block13MongoDb.domain.Persona;
import com.Bosonit.Block13MongoDb.exceptions.CustomEntityNotFoundException;
import com.Bosonit.Block13MongoDb.exceptions.UnprocessableEntityException;
import com.Bosonit.Block13MongoDb.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements com.Bosonit.Block13MongoDb.application.PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public PersonaOutputDto getPersonaById(String idPersona) {
        return mongoTemplate.findById(idPersona, Persona.class)
                .personaToPersonaOutputDto();
    }

    @Override
    public List<PersonaOutputDto> getPersonaByUsuario(String usuario) {
        List<Persona> personas = personaRepository.findByUsuario(usuario);

        return personas.stream()
                .map(Persona::personaToPersonaOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) throws Exception {
        checkInputDto(personaInputDto);
        Persona persona = new Persona(personaInputDto);
        mongoTemplate.save(persona);
        return persona.personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto updatePersona(String idPersona, PersonaInputDto personaInputDto) throws Exception {
        checkInputDto(personaInputDto);
        Persona existingPersona = mongoTemplate.findById(idPersona, Persona.class);
        if (existingPersona == null) {
            throw new CustomEntityNotFoundException("Persona not found");
        }

        existingPersona.setUsuario(personaInputDto.getUsuario());
        existingPersona.setPassword(personaInputDto.getPassword());
        existingPersona.setName(personaInputDto.getName());
        existingPersona.setSurname(personaInputDto.getSurname());
        existingPersona.setCompanyEmail(personaInputDto.getCompanyEmail());
        existingPersona.setPersonalEmail(personaInputDto.getPersonalEmail());
        existingPersona.setCity(personaInputDto.getCity());
        existingPersona.setActive(personaInputDto.isActive());
        existingPersona.setCreatedDate(personaInputDto.getCreatedDate());
        existingPersona.setImageUrl(personaInputDto.getImageUrl());
        existingPersona.setTerminationDate(personaInputDto.getTerminationDate());

        mongoTemplate.save(existingPersona);
        return existingPersona.personaToPersonaOutputDto();
    }

    private void checkInputDto(PersonaInputDto personaInputDto) throws Exception {

        if (isNulo(personaInputDto.getUsuario())) {
            throw new Exception("Usuario no puede ser nulo");
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
    public void deletePersona(String idPersona) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(idPersona)), Persona.class);
    }

    @Override
    public List<PersonaOutputDto> getAllPersona(int pageNumber, int pageSize) {
        Query query = new Query().with(PageRequest.of(pageNumber, pageSize));
        List<Persona> personas = mongoTemplate.find(query, Persona.class);
        return personas.stream()
                .map(Persona::personaToPersonaOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonaOutputDto> getPersonaByQuery(String usuario, String name, String surname, String companyEmail,
                                                    String personalEmail, String city,
                                                    Boolean active, Date createdDate,
                                                    String imageUrl, Date terminationDate, String orderByField,
                                                    boolean asc, int pageNumber, int pageSize)
    {
        Criteria criteria = new Criteria();

        if (usuario != null) {
            criteria.and("usuario").is(usuario);
        }
        if (name != null) {
            criteria.and("name").is(name);
        }
        if (surname != null) {
            criteria.and("surname").is(surname);
        }
        if (companyEmail != null) {
            criteria.and("companyEmail").is(companyEmail);
        }
        if (personalEmail != null) {
            criteria.and("personalEmail").is(personalEmail);
        }
        if (city != null) {
            criteria.and("city").is(city);
        }
        if (active != null) {
            criteria.and("active").is(active);
        }
        if (createdDate != null) {
            criteria.and("createdDate").is(createdDate);
        }
        if (imageUrl != null) {
            criteria.and("imageUrl").is(imageUrl);
        }
        if (terminationDate != null) {
            criteria.and("terminationDate").is(terminationDate);
        }

        Query query = new Query(criteria).with(PageRequest.of(pageNumber, pageSize));

        if (orderByField != null && asc) {
            query.with(Sort.by(Sort.Order.asc(orderByField)));
        }
        if (orderByField != null && !asc) {
            query.with(Sort.by(Sort.Order.desc(orderByField)));
        }

        List<Persona> personas = mongoTemplate.find(query, Persona.class);
        return personas.stream()
                .map(Persona::personaToPersonaOutputDto)
                .collect(Collectors.toList());
    }



}
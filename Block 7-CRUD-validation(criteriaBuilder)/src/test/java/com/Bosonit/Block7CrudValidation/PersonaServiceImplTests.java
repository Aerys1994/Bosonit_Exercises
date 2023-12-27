package com.Bosonit.Block7CrudValidation;

import com.Bosonit.Block7CrudValidation.application.PersonaServiceImpl;
import com.Bosonit.Block7CrudValidation.controllers.dto.PersonaInputDto;
import com.Bosonit.Block7CrudValidation.controllers.dto.PersonaOutputDto;
import com.Bosonit.Block7CrudValidation.domain.Persona;
import com.Bosonit.Block7CrudValidation.exceptions.CustomEntityNotFoundException;
import com.Bosonit.Block7CrudValidation.exceptions.UnprocessableEntityException;
import com.Bosonit.Block7CrudValidation.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PersonaServiceImplTests {

    @InjectMocks
    PersonaServiceImpl personaServiceImpl;

    @Mock
    PersonaRepository personaRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addPersonaTest() throws Exception {
        // Case null
        PersonaInputDto personaNull = new PersonaInputDto();
        assertThrows(Exception.class, () -> personaServiceImpl.addPersona(personaNull));

        // Case usuario < 6
        PersonaInputDto personaUsernameLength = new PersonaInputDto(1, "12345", "dfgdfg", "Andreu",
                "Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());
        try{
            personaServiceImpl.addPersona(personaUsernameLength);
        } catch (UnprocessableEntityException e) {
            Assertions.assertEquals(e.getMessage(), "Usuario debe tener entre 6 y 10 caracteres");
        }

        // Case usuario > 10
        personaUsernameLength.setUsuario("12345678910");
        try{
            personaServiceImpl.addPersona(personaUsernameLength);
        } catch (UnprocessableEntityException e){
            Assertions.assertEquals(e.getMessage(), "Usuario debe tener entre 6 y 10 caracteres");
        }

        Persona persona = new Persona(1,"123456", "dfgdfg", "Andreu",
                "Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());

        PersonaInputDto personaInputDto = new PersonaInputDto(1,"123456", "dfgdfg", "Andreu",
                "Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());

        PersonaOutputDto personaOutputDto = new PersonaOutputDto(1,"123456", "dfgdfg", "Andreu",
                "Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());

        Mockito.when(personaRepository.save(new Persona(personaInputDto))).thenReturn(persona);

        PersonaOutputDto personaOutputDtoCreated = personaServiceImpl.addPersona(personaInputDto);

        Assertions.assertEquals(personaOutputDtoCreated, personaOutputDto);
    }

    @Test
    public void updatePersonaTest() throws Exception {
        PersonaInputDto personaInputDto = new PersonaInputDto(1,"123456", "dfgdfg", "Andreu",
                "Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());

        Persona persona = new Persona(1,"123456", "dfgdfg", "Andreu",
                "Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());

        // Persona not found
        try {
            personaServiceImpl.updatePersona(1, personaInputDto);
        } catch (CustomEntityNotFoundException e) {
            Assertions.assertEquals(e.getMessage(), "Persona no encontrada con id: 1");
        }

        PersonaOutputDto personaOutputDto = new PersonaOutputDto(1,"123456", "dfgdfg", "Avery",
                "Rooks", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());
        personaInputDto.setName("Avery");
        personaInputDto.setSurname("Rooks");
        Mockito.when(personaRepository.findById(1)).thenReturn(Optional.of(persona));
        Mockito.when(personaRepository.save(new Persona(personaInputDto))).thenReturn(persona);
        PersonaOutputDto personaOutputDto1 = personaServiceImpl.updatePersona(1, personaInputDto);

        Assertions.assertEquals(personaOutputDto1.getIdPersona(), personaOutputDto.getIdPersona());
        Assertions.assertEquals(personaOutputDto1.getName(), personaOutputDto.getName());
        Assertions.assertEquals(personaOutputDto1.getSurname(), personaOutputDto.getSurname());
    }

    @Test
    public void getPersonaByIdTest() throws Exception {
        Persona persona1 = new Persona(1,"123456", "dfgdfg", "Andreu",
                "Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());

        Mockito.when(personaRepository.findById(1)).thenReturn(Optional.of(persona1));
        PersonaOutputDto personaOutputDto = personaServiceImpl.getPersonaById(1);
        Assertions.assertEquals(personaOutputDto, persona1.personaToPersonaOutputDto());

        try {
            personaServiceImpl.getPersonaById(2);
        } catch (CustomEntityNotFoundException e) {
            Assertions.assertEquals(e.getMessage(), "Persona not found with id: 2");
        }
    }

    @Test
    public void getPersonaByUsuarioTest() throws Exception {
        Persona persona1 = new Persona(1,"123456", "dfgdfg", "Andreu",
                "Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());
        Persona persona2 = new Persona(new PersonaInputDto(2,"123456", "dfgdfg", "Andreu",
                "Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date()));

        List<Persona> testList = new ArrayList<>();
        testList.add(persona1);
        testList.add(persona2);
        Mockito.when(personaRepository.findByUsuario("123456")).thenReturn(testList);
        Mockito.when(personaRepository.findByUsuario("654321")).thenReturn(new ArrayList<>());
        Mockito.when(personaRepository.findById(1)).thenReturn(Optional.of(persona1));
        Mockito.when(personaRepository.findById(2)).thenReturn(Optional.of(persona2));
        List<PersonaOutputDto> listReceived = personaServiceImpl.getPersonaByUsuario("123456");
        List<PersonaOutputDto> listExpected = testList
                .stream()
                .map(persona -> persona.personaToPersonaOutputDto())
                .collect(Collectors.toList());
        Assertions.assertEquals(listExpected, listReceived);

        // Case empty list
        listReceived = personaServiceImpl.getPersonaByUsuario("654321");
        listExpected = new ArrayList<>();
        Assertions.assertEquals(listExpected, listReceived);
    }

    public void getAllPersonaTest() {
        Persona persona1 = new Persona(1,"123456", "dfgdfg", "Andreu",
                "Cunill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());
        Persona persona2 = new Persona(2,"123456", "dfgdfg", "Henry",
                "Cavill", "oea@aa.es", "aeo@asdf.es", "Logroño",
                true, new Date(), "1234.jpg", new Date());

        List<Persona> list = new ArrayList<>();
        list.add(persona1);
        list.add(persona2);
        Page<Persona> personaPage = new PageImpl<>(list);
        PageRequest pageRequest = PageRequest.of(1,2);
        Mockito.when(personaRepository.findById(1)).thenReturn(Optional.of(persona1));
        Mockito.when(personaRepository.findById(2)).thenReturn(Optional.of(persona2));
        Mockito.when(personaRepository.findAll(pageRequest)).thenReturn(personaPage);
        List<PersonaOutputDto> listReceived = personaServiceImpl.getAllPersona(1,2);
        List<PersonaOutputDto> listExpected= list
                .stream()
                .map(persona -> persona.personaToPersonaOutputDto())
                .collect(Collectors.toList());
        Assertions.assertEquals(listExpected,listReceived);

    }
}

package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorOutputDto;
import com.bosonit.Block7.CRUD.validation.domain.Persona;
import com.bosonit.Block7.CRUD.validation.domain.Professor;
import com.bosonit.Block7.CRUD.validation.exceptions.CustomEntityNotFoundException;
import com.bosonit.Block7.CRUD.validation.repository.PersonaRepository;
import com.bosonit.Block7.CRUD.validation.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableFeignClients
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    private ProfessorFeignClient professorFeignClient;

    @Override
    public Professor getProfessorById(int id) {
        return professorRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Professor no encontrada con ID: " + id));

    }

    @Override
    public Professor createProfessor(int idProfessor) {

        return professorRepository.findById(idProfessor)
                .orElseThrow(() -> new EntityNotFoundException("Professor " +
                        "with ID: " + idProfessor + " could not be found"));
    }

    @Override
    public ProfessorOutputDto addProfessor(ProfessorInputDto professorInputDto) throws Exception
    {
        Persona persona = personaRepository
                .findById(professorInputDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Persona not found with " +
                        "id: " + professorInputDto.getId()));

        if (persona.getStudent() != null) {
            throw new IllegalStateException("Persona already has a student assigned");
        } else if (persona.getProfessor() != null) {
            throw new IllegalStateException("Persona already has a professor assigned");
        }

        Professor professor = new Professor(professorInputDto);
        professor.setPersona(persona);
        persona.setProfessor(professor);
        return professorRepository.save(professor).professorToProfessorOutputDto();
    }

    @Override
    public ProfessorOutputDto updateProfessor(int idProfessor, ProfessorInputDto professorInputDto) throws Exception
    {
        professorRepository.findById(professorInputDto.getIdProfessor()).orElseThrow(() -> new CustomEntityNotFoundException("Professor no encontrada con ID"));
        return professorRepository.save(new Professor(professorInputDto))
                .professorToProfessorOutputDto();
    }

    @Override
    public List<ProfessorOutputDto> getAllProfessor(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return professorRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Professor::professorToProfessorOutputDto).toList();
    }

    public ProfessorOutputDto getProfessorUsingFeign(int id) {
        return professorFeignClient.getProfessorById(id);
    }
}

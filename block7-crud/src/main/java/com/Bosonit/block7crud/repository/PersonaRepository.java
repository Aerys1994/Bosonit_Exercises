package com.Bosonit.block7crud.repository;

import com.Bosonit.block7crud.controller.dto.PersonaOutputDto;
import com.Bosonit.block7crud.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer>
{
    List<PersonaOutputDto> findByNombre(String nombre);
}

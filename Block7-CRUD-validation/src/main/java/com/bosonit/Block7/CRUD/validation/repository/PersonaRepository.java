package com.bosonit.Block7.CRUD.validation.repository;

import com.bosonit.Block7.CRUD.validation.controllers.dto.PersonaOutputDto;
import com.bosonit.Block7.CRUD.validation.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer>
{
    List<PersonaOutputDto> findByUsuario(String usuario);
}


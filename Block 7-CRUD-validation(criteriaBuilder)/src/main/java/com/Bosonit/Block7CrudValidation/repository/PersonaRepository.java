package com.Bosonit.Block7CrudValidation.repository;

import com.Bosonit.Block7CrudValidation.controllers.dto.PersonaOutputDto;
import com.Bosonit.Block7CrudValidation.domain.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Integer>
{
    Optional<Persona> findByUsuario(String usuario);

    public Page<PersonaOutputDto> findPersonaByQuery(Map<String, Object> conditions, Pageable pageable);

}


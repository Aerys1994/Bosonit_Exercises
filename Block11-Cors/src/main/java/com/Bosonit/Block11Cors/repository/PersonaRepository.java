package com.Bosonit.Block11Cors.repository;

import com.Bosonit.Block11Cors.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer>
{
    List<Persona> findByUsuario(String usuario);
}


package com.Bosonit.Block10dockerization.repository;

import com.Bosonit.Block10dockerization.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer>
{
    List<Persona> findByUsuario(String usuario);
}


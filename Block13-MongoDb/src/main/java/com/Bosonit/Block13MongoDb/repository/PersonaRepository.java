package com.Bosonit.Block13MongoDb.repository;

import com.Bosonit.Block13MongoDb.domain.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PersonaRepository extends MongoRepository<Persona, Integer> {
    List<Persona> findByUsuario(String usuario);
    Page<Persona> findAll(Pageable pageable);
}



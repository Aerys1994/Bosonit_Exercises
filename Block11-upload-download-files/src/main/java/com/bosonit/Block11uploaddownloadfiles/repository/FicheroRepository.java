package com.bosonit.Block11uploaddownloadfiles.repository;

import com.bosonit.Block11uploaddownloadfiles.domain.Fichero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FicheroRepository extends JpaRepository<Fichero, Integer> {
    Optional<Fichero> findByName(String name);
}

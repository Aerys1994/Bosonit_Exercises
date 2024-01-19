package com.bosonit.examen_JPA_cascada.repository;

import com.bosonit.examen_JPA_cascada.domain.LineasFra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineasFraRepository extends JpaRepository<LineasFra, Integer> {
    List<LineasFra> findByIdFra(int idFra);
}

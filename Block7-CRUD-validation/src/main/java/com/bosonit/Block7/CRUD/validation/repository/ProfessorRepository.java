package com.bosonit.Block7.CRUD.validation.repository;

import com.bosonit.Block7.CRUD.validation.domain.Professor;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}

package com.bosonit.Block7.CRUD.validation.repository;

import com.bosonit.Block7.CRUD.validation.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}

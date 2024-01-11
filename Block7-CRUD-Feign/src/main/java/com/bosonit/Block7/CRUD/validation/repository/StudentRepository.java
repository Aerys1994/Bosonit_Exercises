package com.bosonit.Block7.CRUD.validation.repository;

import com.bosonit.Block7.CRUD.validation.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}

package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentOutputDtoSimple;
import com.bosonit.Block7.CRUD.validation.domain.Student;

import java.util.List;


public interface StudentService {

    Student getStudentById(int id);

    List<StudentOutputDtoSimple> getAllStudent (int pageNumber, int pageSize);

    Student createStudent(int idStudent);

    StudentOutputDtoSimple addStudent (StudentInputDto studentInputDto) throws Exception;

    StudentOutputDtoSimple updateStudent(int idStudent, StudentInputDto studentInputDto) throws Exception;



}

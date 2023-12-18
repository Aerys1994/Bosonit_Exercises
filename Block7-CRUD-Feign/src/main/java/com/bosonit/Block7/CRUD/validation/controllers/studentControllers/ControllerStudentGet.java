package com.bosonit.Block7.CRUD.validation.controllers.studentControllers;


import com.bosonit.Block7.CRUD.validation.application.StudentServiceImpl;
import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentOutputDtoSimple;
import com.bosonit.Block7.CRUD.validation.domain.Student;
import com.bosonit.Block7.CRUD.validation.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class ControllerStudentGet {

    @Autowired
    StudentServiceImpl studentServiceImpl;


    @GetMapping("/{id_student}")
    public ResponseEntity<StudentOutputDtoSimple> getStudentById(
            @PathVariable int id_student,
            @RequestParam(defaultValue = "simple") String outputType) {

            Student student = studentServiceImpl.getStudentById(id_student);


            if (outputType.toLowerCase().equals("full")) {
                var dto=student.studentToStudentOutputDtoFull();
                return ResponseEntity.ok().body(dto);
            } else  {
                return ResponseEntity.ok().body(student.studentToStudentOutputDtoSimple());
            }
    }

    @GetMapping
    public List<StudentOutputDtoSimple> getAllStudent(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize) {

        return studentServiceImpl.getAllStudent(pageNumber, pageSize);
    }
}

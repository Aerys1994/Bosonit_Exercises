package com.bosonit.Block7.CRUD.validation.controllers.studentControllers;

import com.bosonit.Block7.CRUD.validation.application.StudentService;
import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentOutputDtoSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class ControllerStudentUpdate {

    @Autowired
    StudentService studentService;
    @PutMapping
    public ResponseEntity<StudentOutputDtoSimple> updateStudent(@RequestBody StudentInputDto student) {
        try {
            studentService.getStudentById(student.getId());
            return  ResponseEntity.ok().body(studentService.addStudent(student));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
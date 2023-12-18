package com.bosonit.Block7.CRUD.validation.controllers.studentControllers;

import com.bosonit.Block7.CRUD.validation.application.StudentServiceImpl;
import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentOutputDtoSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/student")
public class ControllerStudentAdd {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @PostMapping
    public ResponseEntity<StudentOutputDtoSimple> addStudent
            (@RequestBody StudentInputDto student) throws Exception {

        try {
            URI location = URI.create("/student");
            return ResponseEntity.created(location)
                    .body(studentServiceImpl.addStudent(student));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }
}

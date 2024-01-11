package com.bosonit.Block7.CRUD.validation.controllers.personaControllers;

import com.bosonit.Block7.CRUD.validation.application.SubjectServiceImpl;
import com.bosonit.Block7.CRUD.validation.controllers.dto.subject.SubjectInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.subject.SubjectOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class ControllerSubject {

    @Autowired
    private SubjectServiceImpl subjectServiceImpl;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SubjectOutputDto>> getSubjectsByStudentId(@PathVariable int studentId) {
        List<SubjectOutputDto> subjects = subjectServiceImpl.getSubjectsByStudentId(studentId);
        return ResponseEntity.ok(subjects);
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<String> deleteSubject(@PathVariable int subjectId) {
        subjectServiceImpl.deleteSubject(subjectId);
        return ResponseEntity.ok("Subject deleted successfully.");
    }


    @PostMapping("/assign/{studentId}")
    public ResponseEntity<String> assignSubjectsToStudent(@PathVariable int studentId, @RequestBody List<SubjectInputDto> subjectInputDtos) {
        subjectServiceImpl.assignSubjectsToStudent(studentId, subjectInputDtos);
        return ResponseEntity.ok("Subjects assigned to student successfully.");
    }


    @PostMapping("/unassign/{studentId}")
    public ResponseEntity<String> unassignSubjectsFromStudent(@PathVariable int studentId, @RequestBody List<Integer> subjectIds) {
        subjectServiceImpl.unassignSubjectsFromStudent(studentId, subjectIds);
        return ResponseEntity.ok("Subjects unassigned from student successfully.");
    }
}


package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.subject.SubjectInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.subject.SubjectOutputDto;
import com.bosonit.Block7.CRUD.validation.domain.Subject;

import java.util.List;

public interface SubjectService {
    List<SubjectOutputDto> getSubjectsByStudentId(int studentId);
    void deleteSubject(int subjectId);
    List<Subject> assignSubjectsToStudent(int studentId, List<SubjectInputDto> subjects);
    void unassignSubjectsFromStudent(int studentId, List<Integer> subjectIds);
}

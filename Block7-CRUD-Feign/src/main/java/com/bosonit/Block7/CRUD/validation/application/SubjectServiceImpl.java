package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.subject.SubjectInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.subject.SubjectOutputDto;
import com.bosonit.Block7.CRUD.validation.domain.Student;
import com.bosonit.Block7.CRUD.validation.domain.Subject;
import com.bosonit.Block7.CRUD.validation.exceptions.CustomEntityNotFoundException;
import com.bosonit.Block7.CRUD.validation.repository.StudentRepository;
import com.bosonit.Block7.CRUD.validation.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<SubjectOutputDto> getSubjectsByStudentId(int idStudent) {
        Student student = studentRepository.findById(idStudent).orElseThrow(
                () -> new CustomEntityNotFoundException("Student no encontrada con ID: " + idStudent));

        return student.getSubjectList().stream()
                .map(Subject::subjectToSubjectOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Subject> assignSubjectsToStudent(int idStudent, List<SubjectInputDto> subjectInputDtos) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + idStudent + " not found"));

        List<Subject> subjectList = subjectInputDtos.stream()
                .map(subjectInputDto -> {
                    Subject subject = new Subject(subjectInputDto);
                    subject.setStudent(student);
                    return subject;
                })
                .collect(Collectors.toList());

        return subjectRepository.saveAll(subjectList);
    }


    @Override
    public void unassignSubjectsFromStudent(int idStudent, List<Integer> subjectIds) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + idStudent + " not found"));

        List<Subject> subjects = subjectRepository.findAllById(subjectIds);

        subjects.forEach(subject -> {
            if (subject.getStudent() != null && subject.getStudent().getIdStudent() == idStudent) {
                subject.setStudent(null);
            }
        });

        subjectRepository.saveAll(subjects);
    }

    @Override
    public void deleteSubject(int subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject with ID " + subjectId + " not found"));

        if (!subject.getStudent().getSubjectList().isEmpty()) {
            throw new CustomEntityNotFoundException("Cannot delete subject with assigned students");
        }

        subjectRepository.delete(subject);
    }
}

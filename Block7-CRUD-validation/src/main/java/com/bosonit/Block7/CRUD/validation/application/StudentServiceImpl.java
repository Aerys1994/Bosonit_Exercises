package com.bosonit.Block7.CRUD.validation.application;

import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentOutputDtoSimple;
import com.bosonit.Block7.CRUD.validation.domain.Persona;
import com.bosonit.Block7.CRUD.validation.domain.Student;
import com.bosonit.Block7.CRUD.validation.exceptions.CustomEntityNotFoundException;
import com.bosonit.Block7.CRUD.validation.repository.PersonaRepository;
import com.bosonit.Block7.CRUD.validation.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Student no encontrado con ID: " + id));

    }

    @Override
    public Student createStudent(int idStudent) {

        return studentRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("Student " +
                "with ID: " + idStudent + " could not be found"));
    }

    @Override
    public StudentOutputDtoSimple addStudent(StudentInputDto studentInputDto) throws Exception
    {
        Persona persona = personaRepository
                .findById(studentInputDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Persona not found with " +
                        "id: " + studentInputDto.getId()));

        if (persona.getStudent() != null) {
            throw new IllegalStateException("Persona already has a student assigned");
        } else if (persona.getProfessor() != null) {
            throw new IllegalStateException("Persona already has a professor assigned");
        }

        Student student = new Student(studentInputDto);
        student.setPersona(persona);
        persona.setStudent(student);
        return studentRepository.save(student).studentToStudentOutputDtoSimple();

    }

    @Override
    public StudentOutputDtoSimple updateStudent(int idStudent, StudentInputDto studentInputDto) throws Exception
    {
        studentRepository.findById(studentInputDto.getIdStudent()).orElseThrow(() -> new CustomEntityNotFoundException("Student no encontrada con ID"));
        return studentRepository.save(new Student(studentInputDto))
                .studentToStudentOutputDtoSimple();
    }

    boolean isNulo(String str)
    {
        return str==null || str.isBlank();
    }

    @Override
    public List<StudentOutputDtoSimple> getAllStudent(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Student::studentToStudentOutputDtoSimple).toList();
    }
}

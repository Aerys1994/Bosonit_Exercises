package com.bosonit.Block7.CRUD.validation.domain;

import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentOutputDtoFull;
import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentOutputDtoSimple;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private int idStudent;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, unique = true)
    private Persona persona;
    @NotNull
    private int numHoursWeek;
    @ManyToOne
    Professor professor;
    private String comments;
    @NotNull
    private String branch;
    @OneToMany(mappedBy = "student")
    private List<Subject> subjectList;

    public Student(StudentInputDto studentInputDto) {
        this.numHoursWeek = studentInputDto.getNumHoursWeek();
        this.comments = studentInputDto.getComments();
        this.branch = studentInputDto.getBranch();

    }

    public StudentOutputDtoSimple studentToStudentOutputDtoSimple() {
        return new StudentOutputDtoSimple(
                this.idStudent,
                this.numHoursWeek,
                this.comments,
                this.branch,
                this.persona.getId(),
                this.persona.getName(),
                this.persona.getSurname()
        );
    }

    public StudentOutputDtoFull studentToStudentOutputDtoFull(){
        return new StudentOutputDtoFull(
                this.idStudent,
                this.numHoursWeek,
                this.comments,
                this.branch,
                this.persona.getId(),
                this.persona.getUsuario(),
                this.persona.getName(),
                this.persona.getSurname(),
                this.persona.getCompanyEmail(),
                this.persona.getPersonalEmail(),
                this.persona.getCity(),
                this.persona.isActive(),
                this.persona.getCreatedDate(),
                this.persona.getImageUrl(),
                this.persona.getTerminationDate()
        );
    }
}

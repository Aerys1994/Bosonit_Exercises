package com.bosonit.Block7.CRUD.validation.domain;

import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.professor.ProfessorOutputDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue
    private int idProfessor;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, unique = true)
    private Persona persona;
    private String comments;
    @OneToMany(mappedBy = "professor")
    List<Student> students;
    @NotNull
    private String branch;

    public Professor(ProfessorInputDto professorInputDto) {
        this.comments = professorInputDto.getComments();
        this.branch = professorInputDto.getBranch();
    }

    public ProfessorOutputDto professorToProfessorOutputDto() {
        return new ProfessorOutputDto(
                this.idProfessor,
                this.comments,
                this.branch,
                this.persona.getId(),
                this.persona.getName(),
                this.persona.getSurname()
        );
    }



}

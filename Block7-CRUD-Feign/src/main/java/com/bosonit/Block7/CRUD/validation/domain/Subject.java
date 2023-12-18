package com.bosonit.Block7.CRUD.validation.domain;

import com.bosonit.Block7.CRUD.validation.controllers.dto.subject.SubjectInputDto;
import com.bosonit.Block7.CRUD.validation.controllers.dto.subject.SubjectOutputDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue
    private int idSubject;
    @ManyToOne
    @JoinColumn(name = "idStudent")
    Student student;
    private String subject;
    private String comments;
    @NotNull
    private Date initialDate;
    private Date finishDate;

    public Subject(SubjectInputDto subjectInputDto) {
        this.subject = subjectInputDto.getSubject();
        this.comments = subjectInputDto.getComments();
        this.initialDate = subjectInputDto.getInitialDate();
        this.finishDate = subjectInputDto.getFinishDate();
    }

    public SubjectOutputDto subjectToSubjectOutputDto() {
        return new SubjectOutputDto(
                this.subject,
                this.comments,
                this.initialDate,
                this.finishDate
        );


    }


}

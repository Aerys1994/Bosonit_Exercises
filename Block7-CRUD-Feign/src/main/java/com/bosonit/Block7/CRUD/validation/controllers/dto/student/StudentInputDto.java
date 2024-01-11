package com.bosonit.Block7.CRUD.validation.controllers.dto.student;

import com.bosonit.Block7.CRUD.validation.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {

    int idStudent;
    int id;
    int numHoursWeek;
    String comments;
    // int idProfessor;
    String branch;
    // List<Subject> subjectList;
}

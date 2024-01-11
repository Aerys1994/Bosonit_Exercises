package com.bosonit.Block7.CRUD.validation.controllers.dto.professor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorOutputDto {

    private int idProfessor;
    private String comments;
    private String branch;
    private int id;
    private String name;
    private String surname;
}

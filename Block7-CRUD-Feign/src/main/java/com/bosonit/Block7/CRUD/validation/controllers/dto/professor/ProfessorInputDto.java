package com.bosonit.Block7.CRUD.validation.controllers.dto.professor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorInputDto
{
    private int idProfessor;
    private int id;
    private String comments;
    private String branch;

}

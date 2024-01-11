package com.bosonit.Block7.CRUD.validation.controllers.dto.student;

import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDtoSimple {

    private int idStudent;
    private int numHoursWeek;
    private String comment;
    private String branch;
    private int id;
    private String name;
    private String surname;
}

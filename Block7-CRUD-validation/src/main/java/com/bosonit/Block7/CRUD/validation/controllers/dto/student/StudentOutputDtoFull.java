package com.bosonit.Block7.CRUD.validation.controllers.dto.student;

import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

@NoArgsConstructor
public class StudentOutputDtoFull extends StudentOutputDtoSimple
{
    PersonaOutputDto personaOutputDto;


    public StudentOutputDtoFull(PersonaOutputDto personaOutputDto) {
        this.personaOutputDto = personaOutputDto;
    }

    public StudentOutputDtoFull(int idStudent, int numHoursWeek, String comment, String branch, int id, String usuario, String password, String name, String surname, String companyEmail, String personalEmail, String city, boolean active, Date createdDate, String imageUrl, Date terminationDate) {
        super(idStudent, numHoursWeek, comment, branch, id, name, surname);
        this.personaOutputDto = new PersonaOutputDto(id, usuario, password, name, surname, companyEmail, personalEmail, city, active, createdDate, imageUrl, terminationDate, null, null);
    }
}

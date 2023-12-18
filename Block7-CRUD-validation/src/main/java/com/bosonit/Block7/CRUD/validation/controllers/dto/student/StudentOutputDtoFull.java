package com.bosonit.Block7.CRUD.validation.controllers.dto.student;

import com.bosonit.Block7.CRUD.validation.controllers.dto.persona.PersonaOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDtoFull extends StudentOutputDtoSimple
{
    PersonaOutputDto personaOutputDto;
    public StudentOutputDtoFull(int idStudent, int numHoursWeek, String comments,
                                String branch, int id, String usuario, String name,
                                String surname, String companyEmail, String personalEmail,
                                String city, boolean active, Date createdDate, String imageUrl,
                                Date terminationDate){}
}

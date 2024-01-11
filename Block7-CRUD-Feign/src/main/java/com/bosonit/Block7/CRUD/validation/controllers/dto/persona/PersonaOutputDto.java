package com.bosonit.Block7.CRUD.validation.controllers.dto.persona;

import com.bosonit.Block7.CRUD.validation.controllers.dto.student.StudentOutputDtoFull;
import com.bosonit.Block7.CRUD.validation.domain.Professor;
import com.bosonit.Block7.CRUD.validation.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDto {

    int id;
    String usuario;
    String password;
    String name;
    String surname;
    String companyEmail;
    String personalEmail;
    String city;
    boolean active;
    Date createdDate;
    String imageUrl;
    Date terminationDate;
    Student student;
    Professor professor;

}

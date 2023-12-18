package com.bosonit.Block7.CRUD.validation.controllers.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectOutputDto {
    private String subject;
    private String comments;
    private Date initialDate;
    private Date finishDate;
}

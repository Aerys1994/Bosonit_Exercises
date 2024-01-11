package com.bosonit.Block11uploaddownloadfiles.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FicheroOutputDto {

    private int id;
    private String name;
    private Date uploadDate;
    private String category;
}

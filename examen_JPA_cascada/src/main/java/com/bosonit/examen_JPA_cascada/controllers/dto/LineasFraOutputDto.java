package com.bosonit.examen_JPA_cascada.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineasFraOutputDto {

    private int idLineasFra;
    private String proNomb;
    private Double cantidad;
    private Double precio;
}

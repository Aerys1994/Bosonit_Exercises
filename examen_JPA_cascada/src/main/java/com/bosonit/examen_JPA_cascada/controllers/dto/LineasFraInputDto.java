package com.bosonit.examen_JPA_cascada.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineasFraInputDto {

    private String proNomb;
    private Double cantidad;
    private Double precio;

}

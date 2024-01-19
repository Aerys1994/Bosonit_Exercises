package com.bosonit.examen_JPA_cascada.controllers.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FacturaOutputDto {

    private int idCabeceraFra;
    private ClienteOutputDto clienteOutputDto;
    private Double importeFra= Double.valueOf(0);
    private List<LineasFraOutputDto> lineasFraOutputDtoList;
}

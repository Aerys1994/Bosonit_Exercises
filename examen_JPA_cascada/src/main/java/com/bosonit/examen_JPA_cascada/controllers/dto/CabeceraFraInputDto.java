package com.bosonit.examen_JPA_cascada.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabeceraFraInputDto {

    private ClienteOutputDto clienteOutputDto;
    private Double importeFra;
}

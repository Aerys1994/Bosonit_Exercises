package com.bosonit.examen_JPA_cascada.application;

import com.bosonit.examen_JPA_cascada.controllers.dto.FacturaOutputDto;
import com.bosonit.examen_JPA_cascada.controllers.dto.LineasFraInputDto;
import com.bosonit.examen_JPA_cascada.domain.Cliente;

import java.util.List;

public interface FacturaService {

    List<FacturaOutputDto> getFacturas();

    String deleteFactura(int idFactura);

    String addLineToFra(LineasFraInputDto lineasFraInputDto, int idFra);

    void insertarFacturaDePrueba(Cliente cliente);
}

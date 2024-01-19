package com.bosonit.examen_JPA_cascada.application;

import com.bosonit.examen_JPA_cascada.controllers.dto.FacturaOutputDto;
import com.bosonit.examen_JPA_cascada.controllers.dto.LineasFraInputDto;
import com.bosonit.examen_JPA_cascada.controllers.dto.LineasFraOutputDto;
import com.bosonit.examen_JPA_cascada.domain.CabeceraFra;
import com.bosonit.examen_JPA_cascada.domain.Cliente;
import com.bosonit.examen_JPA_cascada.domain.LineasFra;
import com.bosonit.examen_JPA_cascada.repository.CabeceraFraRepository;
import com.bosonit.examen_JPA_cascada.repository.ClienteRepository;
import com.bosonit.examen_JPA_cascada.repository.LineasFraRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    CabeceraFraRepository cabeceraFraRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    LineasFraRepository lineasFraRepository;


    @Override
    public List<FacturaOutputDto> getFacturas() {
        List<CabeceraFra> cabeceras = cabeceraFraRepository.findAll();
        List<FacturaOutputDto> facturaOutputDtos = new ArrayList<>();

        for (CabeceraFra cabecera : cabeceras) {
            FacturaOutputDto facturaOutputDto = new FacturaOutputDto();
            facturaOutputDto.setIdCabeceraFra(cabecera.getIdCabeceraFra());
            facturaOutputDto.setImporteFra(calcularImporteTotal(cabecera.getLineasFra()));
            facturaOutputDto.setClienteOutputDto(cabecera.getCliente().clienteToClienteOutputDto());
            facturaOutputDto.setLineasFraOutputDtoList(cabecera.getLineasFra()
                    .stream()
                    .map(lineasFra -> {
                        LineasFraOutputDto lineasFraOutputDto = lineasFra.lineasFraToLineasFraOutputDto();
                        return  lineasFraOutputDto;
                    })
                    .toList());

            facturaOutputDtos.add(facturaOutputDto);

        }
        return facturaOutputDtos;
    }

    @Override
    public String deleteFactura(int idFra) {
        cabeceraFraRepository.deleteById(idFra);
        return "Factura was succesfully deleted";
    }

    @Override
    @Transactional
    public String addLineToFra(LineasFraInputDto lineasFraInputDto, int idFactura) {
        CabeceraFra cabeceraFra = cabeceraFraRepository.findById(idFactura)
                .orElseThrow(() -> new EntityNotFoundException("Factura not found with ID: " + idFactura));

        LineasFra nuevaLinea = new LineasFra(lineasFraInputDto);
        nuevaLinea.setCabeceraFra(cabeceraFra);
        cabeceraFra.getLineasFra().add(nuevaLinea);

        cabeceraFraRepository.save(cabeceraFra);

        return "Line correctly added";
    }

    @Override
    public void insertarFacturaDePrueba(Cliente cliente) {
        CabeceraFra cabeceraFra = new CabeceraFra();
        cabeceraFra.setCliente(cliente);

        LineasFra linea1 = new LineasFra("Linea1", 10, 15);
        LineasFra linea2 = new LineasFra("Linea2", 5, 22);

        List<LineasFra> lineasFras = Arrays.asList(linea1, linea2);
        double importeFra = lineasFras.stream().mapToDouble(LineasFra::getPrecio).sum();

        cabeceraFra.setImporteFra(importeFra);
        cabeceraFra.setLineasFra(lineasFras);
        linea1.setCabeceraFra(cabeceraFra);
        linea2.setCabeceraFra(cabeceraFra);

        cabeceraFraRepository.save(cabeceraFra);
    }



    private Double calcularImporteTotal(List<LineasFra> lineasFraList) {
        return lineasFraList.stream().mapToDouble(linea -> linea.getCantidad() * linea.getPrecio()).sum();
    }
}

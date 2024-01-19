package com.bosonit.examen_JPA_cascada.controllers;

import com.bosonit.examen_JPA_cascada.application.FacturaService;
import com.bosonit.examen_JPA_cascada.controllers.dto.FacturaOutputDto;
import com.bosonit.examen_JPA_cascada.controllers.dto.LineasFraInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controllers {

    @Autowired
    private FacturaService facturaService;

    @GetMapping("/factura")
    public ResponseEntity<List<FacturaOutputDto>> getFacturas(){
        return ResponseEntity.ok(facturaService.getFacturas());
    }

    @PostMapping("/factura/linea/{idFra}")
    public ResponseEntity<String> addLineaToFra(@PathVariable int idFra, @RequestBody LineasFraInputDto lineasFraInputDto){
        return ResponseEntity.ok(facturaService.addLineToFra(lineasFraInputDto, idFra));
    }

    @DeleteMapping("/factura/{idFra}")
    public ResponseEntity<String> deleteFactura(@PathVariable int idFra){
        return ResponseEntity.ok(facturaService.deleteFactura(idFra));
    }

}

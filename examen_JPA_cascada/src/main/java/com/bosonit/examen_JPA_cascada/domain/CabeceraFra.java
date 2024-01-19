package com.bosonit.examen_JPA_cascada.domain;

import com.bosonit.examen_JPA_cascada.controllers.dto.CabeceraFraInputDto;
import com.bosonit.examen_JPA_cascada.controllers.dto.FacturaOutputDto;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CabeceraFra {

    @Id
    @GeneratedValue
    private int idCabeceraFra;

    @ManyToOne
    @JoinColumn(name = "cli_codi")
    private Cliente cliente;

    private double importeFra;

    @OneToMany(mappedBy = "cabeceraFra", cascade = CascadeType.ALL)
    private List<LineasFra> lineasFra;

    public CabeceraFra(CabeceraFraInputDto cabeceraFraInputDto) {
        this.importeFra = cabeceraFraInputDto.getImporteFra();
    }



}

package com.bosonit.examen_JPA_cascada.domain;

import com.bosonit.examen_JPA_cascada.controllers.dto.LineasFraInputDto;
import com.bosonit.examen_JPA_cascada.controllers.dto.LineasFraOutputDto;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LineasFra {

    @Id
    @GeneratedValue
    private int idLineasFra;

    @ManyToOne
    @JoinColumn(name = "idFra")
    private CabeceraFra cabeceraFra;

    @Column(nullable = false)
    private String proNomb;
    private double cantidad;
    private double precio;

    public LineasFra(LineasFraInputDto lineasFraInputDto) {
        this.proNomb = lineasFraInputDto.getProNomb();
        this.cantidad = lineasFraInputDto.getCantidad();
        this.precio = lineasFraInputDto.getPrecio();
    }

    public LineasFraOutputDto lineasFraToLineasFraOutputDto() {
        return new LineasFraOutputDto(
                this.idLineasFra,
                this.proNomb,
                this.cantidad,
                this.precio
        );
    }

    public LineasFra(@Nonnull String proNomb, double cantidad, double precio) {
        this.proNomb = proNomb;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}

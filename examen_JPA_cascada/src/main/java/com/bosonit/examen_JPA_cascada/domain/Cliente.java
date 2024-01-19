package com.bosonit.examen_JPA_cascada.domain;

import com.bosonit.examen_JPA_cascada.controllers.dto.ClienteInputDto;
import com.bosonit.examen_JPA_cascada.controllers.dto.ClienteOutputDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

    @Id
    @GeneratedValue
    private int idCliente;

    @Column(length = 100, nullable = false)
    private String name;

    public Cliente(ClienteInputDto clienteInputDto) {
        this.name = clienteInputDto.getName();
    }

    public ClienteOutputDto clienteToClienteOutputDto() {
        return new ClienteOutputDto(
                this.idCliente,
                this.name
        );
    }
}

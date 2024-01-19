package com.bosonit.Backend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientOutputDto extends ClientSimpleOutputDto {

    private TripOutputDto trip;

    public ClientOutputDto(int idClient, String name, String surname, int age, String email, String phone, TripOutputDto trip) {
        super(idClient, name, surname, age, email, phone);
        this.trip = trip;
    }
}

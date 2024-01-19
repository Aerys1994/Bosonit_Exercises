package com.bosonit.tripApplication.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private int idClient;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;
}

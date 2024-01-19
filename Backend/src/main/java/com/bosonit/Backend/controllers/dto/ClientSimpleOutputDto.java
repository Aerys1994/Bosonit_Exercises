package com.bosonit.Backend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSimpleOutputDto {

    int idClient;
    String name;
    String surname;
    int age;
    String email;
    String phone;
}

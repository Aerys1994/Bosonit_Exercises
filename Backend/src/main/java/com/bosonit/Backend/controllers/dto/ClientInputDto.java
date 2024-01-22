package com.bosonit.Backend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInputDto {
    String name;
    String surname;
    int age;
    String email;
    String phone;
}
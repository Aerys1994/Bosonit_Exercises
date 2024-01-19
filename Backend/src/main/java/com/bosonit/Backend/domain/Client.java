package com.bosonit.Backend.domain;


import com.bosonit.Backend.controllers.dto.ClientInputDto;

import com.bosonit.Backend.controllers.dto.ClientOutputDto;
import com.bosonit.Backend.controllers.dto.ClientSimpleOutputDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    private int idClient;


    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public Client(ClientInputDto clientInputDto) {
        this.name = clientInputDto.getName();
        this.surname = clientInputDto.getSurname();
        this.age = clientInputDto.getAge();
        this.email = clientInputDto.getEmail();
        this.phone = clientInputDto.getPhone();
    }

    public ClientSimpleOutputDto clientToClientSimpleOutputDto() {
        return new ClientSimpleOutputDto(
                this.idClient,
                this.name,
                this.surname,
                this.age,
                this.email,
                this.phone
        );
    }

    public ClientOutputDto clientToClientOutputDto() {
        return new ClientOutputDto(
                this.idClient,
                this.name,
                this.surname,
                this.age,
                this.email,
                this.phone,
                this.trip != null ? this.trip.tripToTripOutputDto() : null
        );
    }


}

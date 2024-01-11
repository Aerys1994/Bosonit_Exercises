package com.bosonit.Backend.controllers.dto;

import com.bosonit.Backend.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripOutputDto {
    int idTrip;
    String origin;
    String destination;
    Date departureDate;
    Date arrivalDate;
    String status;
    List<Client> passenger;
}

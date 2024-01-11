package com.bosonit.Backend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripInputDto {
    String origin;
    String destination;
    Date departureDate;
    Date arrivalDate;
    String status;

}

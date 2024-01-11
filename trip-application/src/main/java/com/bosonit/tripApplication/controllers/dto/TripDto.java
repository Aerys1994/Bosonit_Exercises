package com.bosonit.tripApplication.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private int idTrip;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private String status;

}

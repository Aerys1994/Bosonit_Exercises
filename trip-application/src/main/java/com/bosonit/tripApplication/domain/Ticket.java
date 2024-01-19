package com.bosonit.tripApplication.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private int id;
    private int passengerId;
    private String passengerName;
    private String passengerLastname;
    private String passengerEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;

}

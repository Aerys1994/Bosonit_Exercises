package com.bosonit.tripApplication.application;

import com.bosonit.tripApplication.controllers.dto.ClientDto;
import com.bosonit.tripApplication.controllers.dto.TripDto;
import com.bosonit.tripApplication.domain.Ticket;

public interface BackendFrontService {

    ClientDto getClientById(int idClient);
    TripDto getTripById(int idTrip);
    Ticket generateTicket(int idClient, int idTrip);
}

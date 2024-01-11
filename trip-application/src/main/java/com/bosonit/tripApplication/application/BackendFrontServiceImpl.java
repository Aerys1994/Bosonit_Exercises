package com.bosonit.tripApplication.application;

import com.bosonit.tripApplication.controllers.dto.ClientDto;
import com.bosonit.tripApplication.controllers.dto.TripDto;
import com.bosonit.tripApplication.domain.Ticket;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BackendFrontServiceImpl implements BackendFrontService {

    @Autowired
    private RestTemplate restTemplate;

    private final String backendBaseUrl = "http://localhost:8080";

    @Override
    public ClientDto getClientById(int idClient) {
        String url = backendBaseUrl + "/client/" + idClient;
        return restTemplate.getForObject(url, ClientDto.class);
    }

    @Override
    public TripDto getTripById(int idTrip) {
        String url = backendBaseUrl + "/trip/" + idTrip;
        return restTemplate.getForObject(url, TripDto.class);
    }

    @Override
    public Ticket generateTicket(int idClient, int idTrip) {
        ClientDto client = getClientById(idClient);
        TripDto trip = getTripById(idTrip);

        if (client == null) {
            throw new EntityNotFoundException("Client not found with ID: " + idClient);
        }

        if (trip == null) {
            throw new EntityNotFoundException("Trip not found with ID: " + idTrip);
        }


        Ticket ticket = new Ticket();
        ticket.setPassengerId(client.getIdClient());
        ticket.setPassengerName(client.getName());
        ticket.setPassengerLastname(client.getSurname());
        ticket.setPassengerEmail(client.getEmail());
        ticket.setTripOrigin(trip.getOrigin());
        ticket.setTripDestination(trip.getDestination());
        ticket.setDepartureDate(trip.getDepartureDate());
        ticket.setArrivalDate(trip.getArrivalDate());


        return ticket;
    }
}

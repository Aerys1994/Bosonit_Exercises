package com.bosonit.Backend.application;

import com.bosonit.Backend.controllers.dto.TripInputDto;
import com.bosonit.Backend.controllers.dto.TripOutputDto;
import com.bosonit.Backend.domain.Client;
import com.bosonit.Backend.domain.Trip;
import com.bosonit.Backend.exceptions.MaxPassengerLimitExceededException;
import com.bosonit.Backend.repository.ClientRepository;
import com.bosonit.Backend.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public TripOutputDto getTripById(int idTrip) {
        Trip trip = tripRepository.findById(idTrip).orElseThrow(
                () -> new EntityNotFoundException("Trip not found with ID: " + idTrip));

        TripOutputDto tripOutputDto = trip.tripToTripOutputDto();

        return tripOutputDto;
    }

    @Override
    public TripOutputDto addTrip(TripInputDto tripInputDto) throws Exception {
        return tripRepository.save(new Trip(tripInputDto))
                .tripToTripOutputDto();
    }

    @Override
    public TripOutputDto updateTrip(int idTrip, TripInputDto tripInputDto) throws Exception {
        Trip trip = tripRepository.findById(idTrip).orElseThrow(() -> new EntityNotFoundException("Trip not found with ID: " + idTrip));

        trip.setOrigin(tripInputDto.getOrigin());
        trip.setDestination(tripInputDto.getDestination());
        trip.setDepartureDate(tripInputDto.getDepartureDate());
        trip.setArrivalDate(tripInputDto.getArrivalDate());
        trip.setStatus(tripInputDto.getStatus());

        return trip.tripToTripOutputDto();
    }

    @Override
    public List<TripOutputDto> getAllTrip(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return tripRepository.findAll(pageRequest).getContent()
                .stream()
                .map(trip -> {
                    return trip.tripToTripOutputDto();
                })
                .toList();
    }

    @Override
    public void deleteTrip(int idTrip) {
        tripRepository.deleteById(idTrip);
    }

    @Override
    public TripOutputDto addClientToTrip(int idTrip, int idClient) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + idClient));

        Trip trip = tripRepository.findById(idTrip)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with ID: " + idTrip));



        // Check if the trip has available seats
        if (trip.getPassenger().size() < 2) {
            client.setTrip(trip);
            trip.getPassenger().add(client);
            clientRepository.save(client);
            tripRepository.save(trip);
        } else {
            throw new MaxPassengerLimitExceededException("Passenger limit exceeded");
        }

        return trip.tripToTripOutputDto();
    }

    public void updateTripStatus(int tripId, String tripStatus) throws Exception {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with ID: " + tripId));

        if ("available".equalsIgnoreCase(tripStatus)) {
            trip.setStatus("available");
        } else if ("unavailable".equalsIgnoreCase(tripStatus)) {
            trip.setStatus("unavailable");
        } else {
            throw new Exception("Invalid trip status: " + tripStatus);
        }

        tripRepository.save(trip);
    }

    public String getTripAvailability(int tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with ID: " + tripId));

        return trip.getStatus();
    }
}


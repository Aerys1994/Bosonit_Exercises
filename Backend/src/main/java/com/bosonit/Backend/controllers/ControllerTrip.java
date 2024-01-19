package com.bosonit.Backend.controllers;

import com.bosonit.Backend.application.TripServiceImpl;
import com.bosonit.Backend.controllers.dto.TripInputDto;
import com.bosonit.Backend.controllers.dto.TripOutputDto;
import com.bosonit.Backend.exceptions.MaxPassengerLimitExceededException;
import com.bosonit.Backend.exceptions.PassengerNotFoundException;
import com.bosonit.Backend.exceptions.TripNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/trip")
public class ControllerTrip {
    @Autowired
    TripServiceImpl tripServiceImpl;

    @PostMapping
    public ResponseEntity<TripOutputDto> addTrip(@RequestBody
                                                     TripInputDto tripInputDto) throws Exception {
        URI location = URI.create("/trip");
        return ResponseEntity.created(location)
                .body(tripServiceImpl.addTrip(tripInputDto));
    }

    @GetMapping("/allTrip")
    public Iterable<TripOutputDto> getAllTrip(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize) {

        return tripServiceImpl.getAllTrip(pageNumber, pageSize);
    }

    @GetMapping("/{idTrip}")
    public ResponseEntity<TripOutputDto> getTripById(@PathVariable int idTrip) {
        try {
            return ResponseEntity.ok().body(tripServiceImpl.getTripById(
                    idTrip));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<TripOutputDto> updateTrip(@RequestBody int idTrip, @RequestBody TripInputDto tripInputDto) {
        try {
            tripServiceImpl.getTripById(idTrip);
            return  ResponseEntity.ok().body(tripServiceImpl.updateTrip(idTrip, tripInputDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public void deleteTrip(@RequestBody int idTrip) {
        deleteTrip(idTrip);
        ResponseEntity.ok().build();
    }

    @PostMapping("/addPassenger/{idTrip}/{idClient}")
    public ResponseEntity<?> addClientToTrip(@PathVariable int idTrip, @PathVariable int idClient) {
        try {

            return ResponseEntity.ok().body(tripServiceImpl.addClientToTrip(idTrip, idClient));



        } catch (MaxPassengerLimitExceededException e) {
            return new ResponseEntity<>("Passenger limit reached", HttpStatus.BAD_REQUEST);
        } catch (TripNotFoundException e) {
            return new ResponseEntity<>("Trip not found", HttpStatus.NOT_FOUND);
        } catch (PassengerNotFoundException e) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/status/{tripId}/{tripStatus}")
    public ResponseEntity<String> updateTripStatus(
            @PathVariable int tripId,
            @PathVariable String tripStatus) throws Exception {
        tripServiceImpl.updateTripStatus(tripId, tripStatus);
        return ResponseEntity.ok("Trip status updated successfully.");
    }

    @GetMapping("/verify/{tripId}")
    public ResponseEntity<String> verifyTripAvailability(@PathVariable int tripId) {
        try {
            String tripStatus = tripServiceImpl.getTripAvailability(tripId);
            return ResponseEntity.ok("Trip availability: " + tripStatus);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Trip not found", HttpStatus.NOT_FOUND);
        }
    }


}

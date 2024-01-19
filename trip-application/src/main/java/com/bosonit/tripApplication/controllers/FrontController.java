package com.bosonit.tripApplication.controllers;

import com.bosonit.tripApplication.application.BackendFrontService;
import com.bosonit.tripApplication.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/front")
public class FrontController {

    @Autowired
    private BackendFrontService backendFrontService;

    @PostMapping("/generateTicket/{idClient}/{idTrip}")
    public ResponseEntity<Ticket> generateTicket(
            @PathVariable int idClient,
            @PathVariable int idTrip) {
        Ticket ticket = backendFrontService.generateTicket(idClient, idTrip);
        return ResponseEntity.ok(ticket);
    }
}

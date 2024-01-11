package com.bosonit.Backend.domain;

import com.bosonit.Backend.controllers.dto.TripInputDto;
import com.bosonit.Backend.controllers.dto.TripOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue
    private int idTrip;

    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private String status;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Client> passenger = new ArrayList<>();

    public Trip(TripInputDto tripInputDto) {
        this.origin = tripInputDto.getOrigin();
        this.destination = tripInputDto.getDestination();
        this.departureDate = tripInputDto.getDepartureDate();
        this.arrivalDate = tripInputDto.getDepartureDate();
        this.status = tripInputDto.getStatus();
        this.passenger = new ArrayList<>();
    }

    public TripOutputDto tripToTripOutputDto() {
        return new TripOutputDto(
                this.idTrip,
                this.origin,
                this.destination,
                this.departureDate,
                this.arrivalDate,
                this.status,
                this.passenger
        );
    }
}

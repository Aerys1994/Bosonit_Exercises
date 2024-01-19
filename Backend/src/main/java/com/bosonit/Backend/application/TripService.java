package com.bosonit.Backend.application;

import com.bosonit.Backend.controllers.dto.TripInputDto;
import com.bosonit.Backend.controllers.dto.TripOutputDto;

import java.util.List;

public interface TripService {

    TripOutputDto getTripById(int id);
    List<TripOutputDto> getAllTrip(int pageNumber, int pageSize);
    TripOutputDto addTrip(TripInputDto tripInputDto) throws Exception;

    TripOutputDto updateTrip(int idTrip, TripInputDto tripInputDto) throws Exception;
    void deleteTrip(int idTrip);
    TripOutputDto addClientToTrip(int idTrip, int idClient);

}

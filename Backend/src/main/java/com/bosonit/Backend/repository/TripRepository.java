package com.bosonit.Backend.repository;

import com.bosonit.Backend.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}

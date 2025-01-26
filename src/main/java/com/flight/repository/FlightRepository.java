package com.flight.repository;


import com.flight.entity.Flight;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, String> {
    List<Flight> findBySourceAndDestinationAndAvailableDate(String source, String destination, LocalDate availableDate);
}

package com.flight.service;


import com.flight.entity.Flight;
import com.flight.model.GetFlights200Response;
import com.flight.model.UpdateFlightAvailableSeats;

import java.time.LocalDate;
import java.util.List;


public interface FlightService {
    public List<GetFlights200Response> searchFlights(String source, String destination, LocalDate availableDate);
    public Flight getFlightDetails(String flightId);
    public void updateFlightDetails(UpdateFlightAvailableSeats updateFlightAvailableSeats);

    //public void deleteByAvailableDate(LocalDate availableDate);
}

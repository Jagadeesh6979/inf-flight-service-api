package com.flight.mapper;


import com.flight.entity.Flight;
import com.flight.model.GetFlights200Response;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightMapper {

    public List<GetFlights200Response> getSearchFlightMapper(List<Flight> flight) {
        return flight.stream()
                .map(this::mapFlightToGetFlightResponse200).collect(Collectors.toList());
    }

    public GetFlights200Response mapFlightToGetFlightResponse200(Flight flight) {
        GetFlights200Response getFlightsResponse200 = new GetFlights200Response();
        getFlightsResponse200.setFlightId(flight.getFlightId());
        getFlightsResponse200.setAirLine(flight.getAirLine());
        getFlightsResponse200.setSource(flight.getSource());
        getFlightsResponse200.setDestination(flight.getDestination());
        getFlightsResponse200.setDepartureTime(flight.getDepartureTime());
        getFlightsResponse200.setArrivalTime(flight.getArrivalTime());
        getFlightsResponse200.setAvailableSeats(flight.getAvailableSeats());
        getFlightsResponse200.setFare(flight.getFare());
        getFlightsResponse200.setAvailableDate(flight.getAvailableDate());
        return getFlightsResponse200;
    }
}


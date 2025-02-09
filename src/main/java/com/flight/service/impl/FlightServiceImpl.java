package com.flight.service.impl;

import com.flight.entity.Flight;
import com.flight.mapper.FlightMapper;
import com.flight.model.FallBack;
import com.flight.model.GetFlights200Response;
import com.flight.model.UpdateFlightAvailableSeats;
import com.flight.repository.FlightRepository;
import com.flight.service.FlightService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private  final FlightMapper flightMapper;
    @Autowired
    private Environment environment;

    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public List<GetFlights200Response> searchFlights(String source, String destination, LocalDate availableDate) {
        if (availableDate.isBefore(LocalDate.now())) throw new RuntimeException(environment.getProperty("flight.date.invalid"));
       List<Flight> response =
               flightRepository.findBySourceAndDestinationAndAvailableDate(source, destination, availableDate);
       if (response.isEmpty()) throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No flights found on selected date.");
       return flightMapper.getSearchFlightMapper(response);
    }

    @Override
    public Flight getFlightDetails(String flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if(flight.isEmpty()) throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No flight found on selected flight.");
        return flight.get();
    }

    @Override
    public void updateFlightDetails(UpdateFlightAvailableSeats updateFlightAvailableSeats) {
       //Flight response = getFlightDetails(flight.getFlightId());
        Flight response = flightRepository.findById(updateFlightAvailableSeats.getFlightId()).get();
        System.out.println("Response from DB: " + response);
        response.setFlightId(response.getFlightId());
        response.setDestination(response.getDestination());
        response.setAvailableDate(response.getAvailableDate());
        response.setSource(response.getSource());
        response.setArrivalTime(response.getArrivalTime());
        response.setDepartureTime(response.getDepartureTime());
        response.setFare(response.getFare());
        response.setAirLine(response.getAirLine());
        response.setAvailableSeats(updateFlightAvailableSeats.getSeatsBooked());
        System.out.println("Before calling save: " + response);
        Flight savedFlight = flightRepository.save(response);
        System.out.println("After calling savedFlight: " + savedFlight);
    }
}

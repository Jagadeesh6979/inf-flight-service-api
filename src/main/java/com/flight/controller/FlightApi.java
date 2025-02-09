package com.flight.controller;

import com.flight.entity.Flight;
import com.flight.model.GetFlights200Response;
import com.flight.model.UpdateFlightAvailableSeats;
import com.flight.service.FlightService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping("flight")
public class FlightApi {

    private final FlightService flightService;

    public FlightApi(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/{source}/{destination}/{date}")
    public ResponseEntity<List<GetFlights200Response>> searchFlights(@PathVariable @NotNull @Pattern(regexp = "[A-Za-z]+",
            message = "Please enter valid source") String source, @PathVariable @NotNull @Pattern(regexp = "[A-Za-z]+",
            message = "Please enter valid destination") String destination, @PathVariable @NotNull LocalDate date) {

        return new ResponseEntity<>(flightService.searchFlights(source, destination, date), HttpStatus.OK) ;
    }

    @GetMapping("/get/{flightId}")
    public ResponseEntity<Flight> getFlightDetails(@PathVariable @NotNull @Pattern(regexp = "^[A-Z]{2}-[0-9]{3}$",
            message = "Please enter valid flight id.") String flightId){
       Flight response = flightService.getFlightDetails(flightId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateFlightDetails(@RequestBody UpdateFlightAvailableSeats updateFlightAvailableSeats) {
        flightService.updateFlightDetails(updateFlightAvailableSeats);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

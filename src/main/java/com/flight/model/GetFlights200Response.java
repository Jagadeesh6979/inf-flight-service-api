package com.flight.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetFlights200Response {
    private String flightId;
    private String airLine;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private Integer availableSeats;
    private LocalDate availableDate;
    private double fare;
}

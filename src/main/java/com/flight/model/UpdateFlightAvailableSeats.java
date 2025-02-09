package com.flight.model;

import lombok.Data;

@Data
public class UpdateFlightAvailableSeats {
    private String flightId;
    private Integer seatsBooked;
}

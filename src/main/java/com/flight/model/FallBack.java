package com.flight.model;

import lombok.Data;

@Data
public class FallBack {
    private String message;
    private String status;
    private Integer errorCode;
}

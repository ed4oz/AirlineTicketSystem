package com.example.demo.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequestDTO {
    private long routeId;
    private String departureAirport;
    private String arrivalAirport;
}

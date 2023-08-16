package com.example.demo.DTO.response;

import com.example.demo.DTO.request.AirlineRequestDTO;
import com.example.demo.DTO.request.RouteRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponseDTO {
    private RouteResponseDTO route;
    private String flightNumber;
    private AirlineResponseDTO airline;
    private String departureTime;
    private String arrivalTime;
    private int capacity;
}

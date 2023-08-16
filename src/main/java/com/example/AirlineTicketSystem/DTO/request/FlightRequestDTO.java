package com.example.demo.DTO.request;

import com.example.demo.entities.Airline;
import com.example.demo.entities.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FlightRequestDTO {

    private String departureTime;
    private String arrivalTime;
    private int capacity;
}

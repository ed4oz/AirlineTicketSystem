package com.example.demo.controller;

import com.example.demo.DTO.request.AirportRequestDTO;
import com.example.demo.DTO.request.FlightRequestDTO;
import com.example.demo.DTO.response.AirportResponseDTO;
import com.example.demo.DTO.response.FlightResponseDTO;
import com.example.demo.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightResponseDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping("/airlineId={airlineId}/routeId={routeId}")
    public FlightResponseDTO addFlight(@PathVariable long airlineId, @PathVariable long routeId, @RequestBody FlightRequestDTO request) {
        return flightService.addFlight(airlineId, routeId, request);
    }

}

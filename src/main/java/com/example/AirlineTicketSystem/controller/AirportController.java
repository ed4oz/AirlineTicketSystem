package com.example.demo.controller;

import com.example.demo.DTO.request.AirportRequestDTO;
import com.example.demo.DTO.response.AirportResponseDTO;
import com.example.demo.entities.Airport;
import com.example.demo.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {
    private AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }
    @GetMapping
    public List<AirportResponseDTO> getAllAirports(){
        return airportService.getAllAirports();
    }
    @PostMapping("/addAirport")
    public AirportResponseDTO addAirport(@RequestBody AirportRequestDTO request){
        return airportService.createAirport(request);
    }
    @GetMapping("/airportName={name}")
    public AirportResponseDTO getAirportByName(@PathVariable String name){
        return airportService.getAirportByName(name);

    }
}

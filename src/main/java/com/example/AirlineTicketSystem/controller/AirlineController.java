package com.example.demo.controller;

import com.example.demo.DTO.request.AirlineRequestDTO;
import com.example.demo.DTO.request.AirportRequestDTO;
import com.example.demo.DTO.response.AirlineResponseDTO;
import com.example.demo.DTO.response.AirportResponseDTO;
import com.example.demo.service.AirlineService;
import com.example.demo.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    private AirlineService airlineService;
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }
    @GetMapping
    public List<AirlineResponseDTO> getAllAirlines(){
        return airlineService.getAllAirlines();
    }
    @PostMapping("/addAirline")
    public AirlineResponseDTO addAirline(@RequestBody AirlineRequestDTO request){
        return airlineService.addAirline(request);
    }
    @GetMapping("/airlineName={name}")
    public AirlineResponseDTO getAirlineByName(@PathVariable String name){
        return airlineService.getAirlineByName(name);

    }

}

package com.example.demo.service;

import com.example.demo.DTO.request.AirportRequestDTO;
import com.example.demo.DTO.response.AirportResponseDTO;
import com.example.demo.entities.Airport;
import com.example.demo.exceptions.ExistObjectException;
import com.example.demo.exceptions.NotExistObjectException;
import com.example.demo.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final ModelMapper mapper;


    public List<AirportResponseDTO> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        List<AirportResponseDTO> response = airports.stream().map(airport -> mapper.map(airport, AirportResponseDTO.class)).toList();

        return response;
    }

    public AirportResponseDTO getAirportByName(String name) {
        if (!airportRepository.existsByNameIgnoreCase(name)){
            throw new NotExistObjectException();
        }
        Airport airport = airportRepository.findByName(name);
        AirportResponseDTO response = mapper.map(airport, AirportResponseDTO.class);

        return response;
    }

    public AirportResponseDTO createAirport(AirportRequestDTO request) {
        if (airportRepository.existsByNameIgnoreCase(request.getName())){
            throw new ExistObjectException();
        }
        Airport airport = mapper.map(request, Airport.class);
        airportRepository.save(airport);

        AirportResponseDTO response = mapper.map(airport, AirportResponseDTO.class);
        return response;
    }
}

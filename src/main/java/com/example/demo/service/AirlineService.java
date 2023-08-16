package com.example.demo.service;

import com.example.demo.DTO.request.AirlineRequestDTO;
import com.example.demo.DTO.response.AirlineResponseDTO;
import com.example.demo.DTO.response.AirportResponseDTO;
import com.example.demo.entities.Airline;
import com.example.demo.exceptions.ExistObjectException;
import com.example.demo.exceptions.NotExistObjectException;
import com.example.demo.repository.AirlineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private final AirlineRepository airlineRepository;

    private final ModelMapper mapper;
    @Transactional
    public AirlineResponseDTO addAirline(AirlineRequestDTO request) {
        if (airlineRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ExistObjectException();
        }
        Airline airline = mapper.map(request, Airline.class);

        airlineRepository.save(airline);
        AirlineResponseDTO response = mapper.map(airline, AirlineResponseDTO.class);
        return response;
    }

    public AirlineResponseDTO getAirlineByName(String name){
        if (!airlineRepository.existsByNameIgnoreCase(name)){
            throw new NotExistObjectException();
        }
        Airline airline=airlineRepository.findByName(name);

        AirlineResponseDTO response=mapper.map(airline, AirlineResponseDTO.class);

        return response;
    }

    public AirlineResponseDTO getAirlineById(long airlineId){
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(() -> new NotExistObjectException());
        AirlineResponseDTO response = mapper.map(airline, AirlineResponseDTO.class);
        return response;
    }

    public List<AirlineResponseDTO> getAllAirlines(){
        List<Airline> airlines= airlineRepository.findAll();

        List<AirlineResponseDTO> response=airlines.stream().map(airline -> mapper.map(airline,AirlineResponseDTO.class)).toList();

        return response;
    }
}

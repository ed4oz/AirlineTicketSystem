package com.example.demo.service;

import com.example.demo.DTO.request.FlightRequestDTO;
import com.example.demo.DTO.response.AirlineResponseDTO;
import com.example.demo.DTO.response.FlightResponseDTO;
import com.example.demo.DTO.response.RouteResponseDTO;
import com.example.demo.entities.Airline;
import com.example.demo.entities.Flight;
import com.example.demo.entities.Route;
import com.example.demo.exceptions.NotExistObjectException;
import com.example.demo.repository.AirlineRepository;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.RouteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    private final ModelMapper mapper;
    private final AirlineRepository airlineRepository;
    private final RouteRepository routeRepository;


    private String generateRandomFlightNumber() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomFlightNumber = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            randomFlightNumber.append(characters.charAt(index));
        }
        return randomFlightNumber.toString();
    }
    @Transactional
    public FlightResponseDTO addFlight(long airlineId, long routeId, FlightRequestDTO request) {

        Route route = routeRepository.findById(routeId).orElseThrow(()-> new NotExistObjectException());
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(() -> new NotExistObjectException());

        Flight flight = mapper.map(request, Flight.class);
        flight.setFlightNumber(generateRandomFlightNumber());
        flight.setAirline(airline);
        flight.setRoute(route);


        flightRepository.save(flight);

        FlightResponseDTO response = mapper.map(flight, FlightResponseDTO.class);
        return response;
    }

    public List<FlightResponseDTO> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();

        List<FlightResponseDTO> response = flights.stream().map(flight -> mapper.map(flight, FlightResponseDTO.class)).toList();

        return response;
    }
}

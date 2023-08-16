package com.example.demo.service;

import com.example.demo.DTO.request.RouteRequestDTO;
import com.example.demo.DTO.response.RouteResponseDTO;
import com.example.demo.entities.Route;
import com.example.demo.exceptions.NotExistObjectException;
import com.example.demo.repository.RouteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper mapper;

    public List<RouteResponseDTO> getAllRoutes() {
        List<Route> routes = routeRepository.findAll();
        List<RouteResponseDTO> response = routes.stream().map(route -> mapper.map(route, RouteResponseDTO.class)).toList();
        return response;
    }

    public RouteResponseDTO getRouteById(Long id) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new NotExistObjectException());
        RouteResponseDTO response = mapper.map(route, RouteResponseDTO.class);
        return response;
    }
    @Transactional
    public RouteResponseDTO addRoute(RouteRequestDTO request) {
        Route route = mapper.map(request, Route.class);
        routeRepository.save(route);
        RouteResponseDTO response = mapper.map(route, RouteResponseDTO.class);
        return response;
    }
}

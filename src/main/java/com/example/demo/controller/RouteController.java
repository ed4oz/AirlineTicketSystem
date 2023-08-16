package com.example.demo.controller;

import com.example.demo.DTO.request.RouteRequestDTO;
import com.example.demo.DTO.response.RouteResponseDTO;
import com.example.demo.service.RouteService;
import com.example.demo.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {
    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService =routeService;
    }
    @GetMapping
    public List<RouteResponseDTO> getAllRoutes(){
        return routeService.getAllRoutes();
    }
    @PostMapping("/addRoute")
    public RouteResponseDTO addRoute(@RequestBody RouteRequestDTO request){
        return routeService.addRoute(request);
    }
    @GetMapping("/routeId={id}")
    public RouteResponseDTO getRouteById(@PathVariable Long id){
        return routeService.getRouteById(id);

    }
}

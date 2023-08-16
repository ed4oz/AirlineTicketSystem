package com.example.demo.controller;

import com.example.demo.DTO.request.TicketRequestDTO;
import com.example.demo.DTO.response.BuyTicketResponseDTO;
import com.example.demo.DTO.response.TicketResponseDTO;
import com.example.demo.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/buyTicket/flightId={flightId}")
    public BuyTicketResponseDTO buyTicket(@RequestBody TicketRequestDTO request, @PathVariable long flightId) {
        return ticketService.buyTicket(request, flightId);
    }

    @PutMapping("/cancelTicket={ticketNumber}")
    public String cancelTicket(@PathVariable String ticketNumber) {
        return ticketService.cancelTicket(ticketNumber);
    }

    @GetMapping
    public List<TicketResponseDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/ticketNumber={ticketNumber}")
    public TicketResponseDTO getTicketByTicketNumber(@PathVariable String ticketNumber) {
        return ticketService.getTicketByTicketNumber(ticketNumber);
    }
}

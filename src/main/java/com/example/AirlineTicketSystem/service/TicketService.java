package com.example.demo.service;

import com.example.demo.DTO.request.TicketRequestDTO;
import com.example.demo.DTO.response.BuyTicketResponseDTO;
import com.example.demo.DTO.response.TicketResponseDTO;
import com.example.demo.entities.Flight;
import com.example.demo.entities.Ticket;
import com.example.demo.exceptions.NotExistObjectException;
import com.example.demo.exceptions.NotExistsTicketNumberException;
import com.example.demo.exceptions.UnavaliableSeatException;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final ModelMapper mapper;
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;


    private String generateRandomTicketNumber() {
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
    public BuyTicketResponseDTO buyTicket(TicketRequestDTO request, long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new NotExistObjectException());

        if (ticketRepository.existsBySeatNumberAndFlightAndAvaliable(request.getSeatNumber(), flight, false)) {
            throw new UnavaliableSeatException();
        }


        Ticket ticket = mapper.map(request, Ticket.class);
        ticket.setFlight(flight);
        ticket.setTicketNumber(generateRandomTicketNumber());
        ticket.setAvaliable(false);

        ticketRepository.save(ticket);
        String maskedCardNumber = maskCCNumber(request.getCc());

        BuyTicketResponseDTO response = mapper.map(ticket, BuyTicketResponseDTO.class);
        response.setCc(maskedCardNumber);

        return response;

    }

    public String maskCCNumber(String ccNumber) {
        ccNumber = ccNumber.replaceAll("[\\s\\-_,]", "");
        int numberLength = ccNumber.length();

        String first6Number = ccNumber.substring(0, 6);
        String last4Number = ccNumber.substring(numberLength - 4);

        StringBuilder maskedMiddleOfNumber = new StringBuilder();

        for (int i = 6; i < numberLength - 4; i++) {
            maskedMiddleOfNumber.append("*");
        }

        String maskedCardNumber = first6Number + maskedMiddleOfNumber + last4Number;

        return maskedCardNumber;

    }

    @Transactional
    public String cancelTicket(String ticketNumber) {
        if (!ticketRepository.existsByTicketNumber(ticketNumber)) {
            throw new NotExistsTicketNumberException();
        }

        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber);
        ticket.setAvaliable(true);

        String message = "Ticket has been canceled!";
        return message;
    }

    public List<TicketResponseDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();

        List<TicketResponseDTO> response = tickets.stream().map(ticket -> mapper.map(ticket, TicketResponseDTO.class)).toList();

        return response;
    }

    public TicketResponseDTO getTicketByTicketNumber(String ticketNumber){
        if (!ticketRepository.existsByTicketNumber(ticketNumber)) {
            throw new NotExistsTicketNumberException();
        }

        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber);
        TicketResponseDTO response = mapper.map(ticket, TicketResponseDTO.class);

        return response;
    }


}

package com.example.demo.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyTicketResponseDTO {
    private String ticketNumber;
    private FlightResponseDTO flight;
    private double price;
    private String passengerName;
    private String seatNumber;
    private String cc;
}

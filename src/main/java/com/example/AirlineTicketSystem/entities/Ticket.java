package com.example.AirlineTicketSystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ticketId;
    private String ticketNumber;
    @ManyToOne
    @JoinColumn(name = "flight_id")

    private double price;
    private String passengerName;
    private String seatNumber;
    private boolean avaliable;

}

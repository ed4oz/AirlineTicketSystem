package com.example.demo.entities;

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
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long flightId;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    private String flightNumber;
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;
    private String departureTime;
    private String arrivalTime;
    private int capacity;
}

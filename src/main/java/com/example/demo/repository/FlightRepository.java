package com.example.demo.repository;

import com.example.demo.entities.Airport;
import com.example.demo.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    Flight findByFlightNumber(String flightNumber);

    boolean existsByFlightNumberIgnoreCase(String flightNumber);
}

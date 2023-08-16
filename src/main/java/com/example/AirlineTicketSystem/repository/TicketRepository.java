package com.example.demo.repository;

import com.example.demo.entities.Flight;
import com.example.demo.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Ticket findByTicketNumber(String ticketNumber);
    boolean existsBySeatNumberAndFlightAndAvaliable(String seatNumber, Flight flight, boolean avaliable);

    boolean existsByTicketNumber(String ticketNumber);
}

package com.example.demo.repository;

import com.example.demo.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByName(String name);
    boolean existsByNameIgnoreCase(String name);
}

package com.example.demo.repository;

import com.example.demo.entities.Airline;
import com.example.demo.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository <Airline,Long>{
    Airline findByName(String name);
    boolean existsByNameIgnoreCase(String name);
}

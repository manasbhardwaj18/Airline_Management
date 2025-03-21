package org.example.airline_management.repositories;

import org.example.airline_management.dto.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByIsActiveTrue();
    Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findByOriginAndDestination(String origin, String destination);
    List<Flight> findByAirline(String airline);
    boolean existsByFlightNumber(String flightNumber);
} 
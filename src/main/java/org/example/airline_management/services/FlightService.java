package org.example.airline_management.services;

import org.example.airline_management.dto.Flight;
import org.example.airline_management.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FlightService {
    
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAvailableFlights() {
        return flightRepository.findByIsActiveTrue();
    }

    public Flight findFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found with number: " + flightNumber));
    }

    public List<String> getAvailableDatesForFlight(String flightNumber) {
        Flight flight = findFlightByNumber(flightNumber);
        return flight.getAvailableDates() != null ? flight.getAvailableDates() : Collections.emptyList();
    }

    public Flight createFlight(Flight flight) {
        if (flightRepository.existsByFlightNumber(flight.getFlightNumber())) {
            throw new RuntimeException("Flight already exists with number: " + flight.getFlightNumber());
        }
        return flightRepository.save(flight);
    }

    public Flight updateFlight(String flightNumber, Flight flightDetails) {
        Flight flight = findFlightByNumber(flightNumber);
        
        // Update the flight details
        flight.setOrigin(flightDetails.getOrigin());
        flight.setDestination(flightDetails.getDestination());
        flight.setDepartureTime(flightDetails.getDepartureTime());
        flight.setArrivalTime(flightDetails.getArrivalTime());
        flight.setPrice(flightDetails.getPrice());
        flight.setAvailableSeats(flightDetails.getAvailableSeats());
        flight.setAvailableDates(flightDetails.getAvailableDates());
        flight.setAircraft(flightDetails.getAircraft());
        flight.setAirline(flightDetails.getAirline());
        flight.setTerminal(flightDetails.getTerminal());
        flight.setGate(flightDetails.getGate());
        flight.setStatus(flightDetails.getStatus());
        
        return flightRepository.save(flight);
    }

    public void deleteFlight(String flightNumber) {
        Flight flight = findFlightByNumber(flightNumber);
        flightRepository.delete(flight);
    }

    public List<Flight> searchFlights(String origin, String destination) {
        return flightRepository.findByOriginAndDestination(origin, destination);
    }

    public List<Flight> getFlightsByAirline(String airline) {
        return flightRepository.findByAirline(airline);
    }
}
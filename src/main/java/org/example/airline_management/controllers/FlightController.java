package org.example.airline_management.controllers;

import org.example.airline_management.dto.Flight;
import org.example.airline_management.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAvailableFlights() {
        return ResponseEntity.ok(flightService.getAvailableFlights());
    }

    @GetMapping("/{flightNumber}")
    public ResponseEntity<Flight> getFlight(@PathVariable String flightNumber) {
        return ResponseEntity.ok(flightService.findFlightByNumber(flightNumber));
    }

    @GetMapping("/{flightNumber}/dates")
    public ResponseEntity<List<String>> getAvailableDates(@PathVariable String flightNumber) {
        return ResponseEntity.ok(flightService.getAvailableDatesForFlight(flightNumber));
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.createFlight(flight));
    }

    @PutMapping("/{flightNumber}")
    public ResponseEntity<Flight> updateFlight(@PathVariable String flightNumber, @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.updateFlight(flightNumber, flight));
    }

    @DeleteMapping("/{flightNumber}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String flightNumber) {
        flightService.deleteFlight(flightNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination) {
        return ResponseEntity.ok(flightService.searchFlights(origin, destination));
    }

    @GetMapping("/airline/{airline}")
    public ResponseEntity<List<Flight>> getFlightsByAirline(@PathVariable String airline) {
        return ResponseEntity.ok(flightService.getFlightsByAirline(airline));
    }
}
package org.example.airline_management.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
    private int availableSeats;
    private boolean isActive;
    
    @ElementCollection
    private List<String> availableDates;
    
    // Additional fields for flight details
    private String aircraft;
    private String airline;
    private String terminal;
    private String gate;
    
    // Status fields
    private String status; // (SCHEDULED, DELAYED, CANCELLED, etc.)
    private boolean isDelayed;
    private int delayMinutes;
}
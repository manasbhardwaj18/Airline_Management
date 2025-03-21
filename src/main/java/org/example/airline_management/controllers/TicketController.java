package org.example.airline_management.controllers;

import org.example.airline_management.dto.Ticket;
import org.example.airline_management.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/reservations")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Object> bookTicket(@RequestBody Ticket ticket) {
        if (ticket.getFlightNumber() == null || ticket.getPassengerName() == null || ticket.getPassengerEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Required information missing: flight number, passenger name, or email.");
        }
        ticket.setReservationTime(LocalDateTime.now());
        Ticket bookedTicket = ticketService.registerBooking(ticket);
        return new ResponseEntity<>(bookedTicket, HttpStatus.CREATED);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Ticket> getReservation(@PathVariable String reservationId) {
        Ticket ticket = ticketService.findReservationByNumber(reservationId);
        return (ticket != null) ? ResponseEntity.ok(ticket) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable String reservationId) {
        boolean cancelled = ticketService.voidReservation(reservationId);
        return (cancelled)
                ? ResponseEntity.ok("Reservation cancelled successfully.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found.");
    }
}
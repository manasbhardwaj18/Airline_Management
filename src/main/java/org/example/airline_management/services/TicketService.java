package org.example.airline_management.services;

import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.example.airline_management.dto.Ticket; 


@Service
public class TicketService {
    private final List<Ticket> bookingRepository = new ArrayList<>();

    public List<Ticket> getAllReservations() {
        return new ArrayList<>(bookingRepository);
    }

    public Ticket findReservationByNumber(String reservationId) {
        return bookingRepository.stream()
                .filter(reservation -> reservation.getTicketNumber().equals(reservationId))
                .findFirst()
                .orElse(null);
    }

    public Ticket registerBooking(Ticket reservation) {
        bookingRepository.add(reservation);
        return reservation;
    }

    public boolean voidReservation(String reservationId) {
        Optional<Ticket> booking = bookingRepository.stream()
                .filter(reservation -> reservation.getTicketNumber().equals(reservationId))
                .findFirst();

        if (booking.isPresent()) {
            booking.get().setVoid(true);
            return true;
        }
        return false;
    }

    public List<Ticket> findReservationsByEmail(String email) {
        return bookingRepository.stream()
                .filter(reservation -> reservation.getContactEmail().equals(email))
                .collect(Collectors.toList());
    }
}
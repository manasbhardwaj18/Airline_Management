package org.example.airline_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String ticketNumber;
    private String flightNumber;
    private String passengerName;
    private String passengerEmail;
    private boolean isVoid;
    private String contactEmail;
    public void setVoid(boolean isVoid) {
        this.isVoid = isVoid;
    }

    public boolean isVoid() {
        return isVoid;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    private boolean isCancelled;
    private String seatNumber;
    private LocalDateTime reservationTime;
    private double fareAmount;
    private String journeyDate;
    private boolean hasCheckedBaggage;
}
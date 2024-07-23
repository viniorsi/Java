package com.viniorsi.TravelEase.Domain.Travel.DTO;


import com.viniorsi.TravelEase.Domain.Ticket.DTO.DTOTicketPerson;

import java.time.LocalDateTime;
import java.util.List;

public record DTOTravelRequest(
        String cpfUser,
        Long destiny_id,
        String departureLocation,
        Long hotel_id,
        int adults_count,
        int kids_count,
        int pet,
        LocalDateTime departureDate,
        LocalDateTime returnDate,
        Boolean isRoundTrip,
        int ticketscount,
        List<DTOTicketPerson> ticketsList
) {
}

package com.br.TravelEase.Travel.Domain.Travel.DTO;


import com.br.TravelEase.Travel.Domain.Ticket.DTO.DTOTicketPerson;

import java.time.LocalDate;
import java.util.List;

public record DTOTravelRequest(
        Long user_id,
        Long destiny_id,
        String departureLocation,
        Long hotel_id,
        int adults_count,
        int kids_count,
        int pet,
        LocalDate departureDate,
        LocalDate returnDate,
        Boolean isRoundTrip,
        int ticketscount,
        List<DTOTicketPerson> ticketsList
) {
}

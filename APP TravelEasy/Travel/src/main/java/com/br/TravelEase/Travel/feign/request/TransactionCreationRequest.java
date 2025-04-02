package com.br.TravelEase.Travel.feign.request;

import com.br.TravelEase.Travel.Domain.Ticket.DTO.DTOTicketPerson;
import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelRequest;
import com.br.TravelEase.Travel.Domain.Travel.Entity.Travel;

import java.math.BigDecimal;
import java.util.List;

public record TransactionCreationRequest(
        List<DTOTicketPerson> ticketsList,
        Long user_id,
        Long travel_id,
        BigDecimal value

) {
    public TransactionCreationRequest(DTOTravelRequest dtoTravelRequest, Travel travel,BigDecimal value) {
        this(dtoTravelRequest.ticketsList(),dtoTravelRequest.user_id(),travel.getId(),value);

    }

}

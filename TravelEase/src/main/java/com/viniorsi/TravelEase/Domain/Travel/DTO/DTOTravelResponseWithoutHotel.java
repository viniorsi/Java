package com.viniorsi.TravelEase.Domain.Travel.DTO;

import com.viniorsi.TravelEase.Domain.Transaction.Entity.Transaction;
import com.viniorsi.TravelEase.Domain.Travel.Entity.Travel;

import java.time.LocalDateTime;

public record DTOTravelResponseWithoutHotel(
        String cpf,
        String destinationName,
        String departureLocation,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        boolean roundTrip,
        int ticketCount,
        int points,
        double value,
        Long transactional_id,
        String stripe_transaction_id

) implements DTOTravelResponse{

    public DTOTravelResponseWithoutHotel(Travel travel, Transaction transaction) {
        this(travel.getUser().getCpf(),
                travel.getDestiny().getCountry().getName(),
                travel.getDepartureLocation().getName(),
                travel.getDepartureDate(),
                travel.getReturn_date(),
                travel.isRoundTrip(),
                travel.getTicketsCount(),
                transaction.getPoints(),
                transaction.getValue(),
                transaction.getId(),
                transaction.getStripe_transaction_id()
        );

    }




}

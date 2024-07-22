package com.viniorsi.TravelEase.Domain.Travel.DTO;

import com.viniorsi.TravelEase.Domain.Transactional.Entity.Transactional;
import com.viniorsi.TravelEase.Domain.Travel.Entity.Travel;
import com.viniorsi.TravelEase.Domain.TravelHotels.Entity.TravelHotels;

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
        Long transactional_id

) implements DTOTravelResponse{

    public DTOTravelResponseWithoutHotel(Travel travel, Transactional transactional) {
        this(travel.getUser().getCpf(),
                travel.getDestiny().getCountry().getName(),
                travel.getDepartureLocation().getName(),
                travel.getDepartureDate(),
                travel.getReturn_date(),
                travel.isRoundTrip(),
                travel.getTicketsCount(),
                transactional.getPoints(),
                transactional.getValue(),
                transactional.getId());
    }




}

package com.viniorsi.TravelEase.Domain.Travel.DTO;

import com.viniorsi.TravelEase.Domain.Transactional.Entity.Transactional;
import com.viniorsi.TravelEase.Domain.Travel.Entity.Travel;
import com.viniorsi.TravelEase.Domain.TravelHotels.Entity.TravelHotels;

import java.time.LocalDateTime;

public record DTOTravelResponseWithHotel  (
        String cpf,
        String destinationName,
        String departureLocation,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        boolean roundTrip,
        int ticketCount,
        int points,
        double value,
        String hotelName,
        int adultCount,
        int childCount,
        boolean pet,
        Long transactional_id
)implements DTOTravelResponse{


    public DTOTravelResponseWithHotel(TravelHotels travelHotels, Travel travel, Transactional transactional) {
        this(travel.getUser().getCpf(),
                travel.getDestiny().getCountry().getName(),
                travel.getDepartureLocation().getName(),
                travel.getDepartureDate(),
                travel.getReturn_date(),
                travel.isRoundTrip(),
                travel.getTicketsCount(),
                transactional.getPoints(),
                transactional.getValue(),
                travelHotels.getHotels().getName(),
                travelHotels.getAdult_count(),
                travelHotels.getKids_count(),
                travelHotels.getHotels().isPet(),
                transactional.getId());
    }
}

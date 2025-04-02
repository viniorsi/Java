package com.br.TravelEase.Travel.feign.response;

import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TravelResponse(
        @NotNull Long user_id,
        @NotNull String destinationName,
        @NotNull String departureLocation,
        @NotNull LocalDateTime departureDate,
        @NotNull LocalDateTime arrivalDate,
        @NotNull Boolean roundTrip,
        @NotNull Integer ticketCount,
        @NotNull Integer points,
        @NotNull BigDecimal value,
        @NotNull UUID transactional_id,
        String hotelName,
        Integer adultCount,
        Integer childCount,
        int pet


) {
    public TravelResponse(DTOTravelRequest request, String destiny, BigDecimal value, UUID transactional_id,int points,String hotel) {
        this(
                request.user_id(),
                destiny,
                request.departureLocation(),
                request.departureDate(),
                request.returnDate(),
                request.isRoundTrip(),
                request.ticketscount(),
                points,
                value,
                transactional_id,
                hotel,
                request.adults_count(),
                request.kids_count(),
                request.pet()
        );

    }

}

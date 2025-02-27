package com.br.TravelEase.Travel.feign.request;

import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HotelReservaRequest(
        Long userId,
        Long quartoId,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate check_in,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate check_out
) {
    public HotelReservaRequest(DTOTravelRequest request, Long quartoId) {
        this(request.user_id(),quartoId,request.departureDate(),request.returnDate());
    }
}

package com.viniorsi.TravelEase.Domain.Airplane.Airplane.DTO;

import com.viniorsi.TravelEase.Domain.Airplane.Airplane.Entity.Airplane;
import com.viniorsi.TravelEase.Domain.Airplane.Seat.DTO.DTOSeat;

import java.util.List;
import java.util.stream.Collectors;

public record DTOAirplaneSeats(
        Long id,
        String model,
        String registration_number,
        int capacity,
        String status,
        String airline,
        List<DTOSeat> seats
) {
    public DTOAirplaneSeats(Airplane airplane) {
        this(
                airplane.getId(),
                airplane.getModel(),
                airplane.getRegistration_number(),
                airplane.getCapacity(),
                airplane.getStatus().name(),
                airplane.getAirline(),
                airplane.getSeats().stream().map(DTOSeat::new).collect(Collectors.toList())
        );
    }
}

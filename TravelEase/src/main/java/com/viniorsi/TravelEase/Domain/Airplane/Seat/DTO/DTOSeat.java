package com.viniorsi.TravelEase.Domain.Airplane.Seat.DTO;

import com.viniorsi.TravelEase.Domain.Airplane.Seat.Entity.Seat;

public record DTOSeat(
        Long id,
        String seat_number,
        String status
) {
    public DTOSeat(Seat seat) {
        this(
                seat.getId(),
                seat.getSeat_number(),
                seat.getStatus().name()
        );
    }
}

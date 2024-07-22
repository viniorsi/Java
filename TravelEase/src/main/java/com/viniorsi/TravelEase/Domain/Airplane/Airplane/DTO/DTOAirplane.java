package com.viniorsi.TravelEase.Domain.Airplane.Airplane.DTO;

import com.viniorsi.TravelEase.Domain.Airplane.Airplane.Entity.Airplane;

public record DTOAirplane(
        Long id,
        String model,
        String registrationNumber,
        int capacity,
        String status,
        String airline
) {
    public DTOAirplane(Airplane airplane) {
        this(
                airplane.getId(),
                airplane.getModel(),
                airplane.getRegistration_number(),
                airplane.getCapacity(),
                airplane.getStatus().getDescricao(),
                airplane.getAirline()
        );
    }

}

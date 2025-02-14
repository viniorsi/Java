package com.br.TravelEase.Travel.Domain.Destiny.DTO;

import com.br.TravelEase.Travel.Domain.Destiny.Entity.Destiny;
import com.viniorsi.TravelEase.Domain.Airplane.Airplane.DTO.DTOAirplane;
import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;

import java.util.List;
import java.util.stream.Collectors;

public record DTOListDestinations(
        Long id,
        String country,
        String description,
        String image_url,
        Double daily_value,
        String continent,
//        List<DTOAirplane> airplanes

) {

    public DTOListDestinations(Destiny destiny) {
        this(
                destiny.getId(),
                destiny.getCountry().getName(),
                destiny.getDescription(),
                destiny.getImage_url(),
                destiny.getDaily_value(),
                destiny.getContinent().getName(),
                destiny.getAirplanes().stream().map(DTOAirplane::new).collect(Collectors.toList())

        );
    }


}

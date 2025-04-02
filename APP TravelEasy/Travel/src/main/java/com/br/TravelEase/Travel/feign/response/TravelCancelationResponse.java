package com.br.TravelEase.Travel.feign.response;


import com.br.TravelEase.Travel.Domain.Travel.Entity.Travel;
import com.br.TravelEase.Travel.Domain.Travel.Enums.TravelStatus;

import java.util.UUID;

public record TravelCancelationResponse(
        Long travelId,
        UUID transactionId,
        TravelStatus travelStatus

) {

   public TravelCancelationResponse(Travel travel){
        this(travel.getId(),travel.getTransaction_id(),travel.getTravelStatus());
    }

}

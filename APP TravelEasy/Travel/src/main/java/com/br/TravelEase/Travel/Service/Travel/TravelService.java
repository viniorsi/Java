package com.br.TravelEase.Travel.Service.Travel;

import com.br.TravelEase.Travel.Domain.Destiny.Entity.Destiny;
import com.br.TravelEase.Travel.Domain.Destiny.Repository.DestinyRepository;
import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelRequest;
import com.br.TravelEase.Travel.Domain.Travel.Entity.Travel;
import com.br.TravelEase.Travel.Domain.Travel.Enums.TravelStatus;
import com.br.TravelEase.Travel.Domain.Travel.Repository.TravelRepository;
import com.br.TravelEase.Travel.feign.HotelClient;
import com.br.TravelEase.Travel.feign.TransactionClient;
import com.br.TravelEase.Travel.feign.request.HotelReservaRequest;
import com.br.TravelEase.Travel.feign.request.TransactionCreationRequest;
import com.br.TravelEase.Travel.feign.response.HotelDetailResponse;
import com.br.TravelEase.Travel.feign.response.TransactionResponse;
import com.br.TravelEase.Travel.feign.response.TravelCancelationResponse;
import com.br.TravelEase.Travel.feign.response.TravelResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class TravelService {

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    DestinyRepository destinyRepository;

    HotelClient hotelClient;

    TransactionClient transactionClient;

    @Transactional
    public TravelResponse addTravel(DTOTravelRequest dtoTravelRequest) {

        Destiny destiny = destinyRepository.findById(dtoTravelRequest.destiny_id())
                .orElseThrow(() -> new RuntimeException("Destiny not found"));

        Long quartoId = dtoTravelRequest.quarto_id();

        BigDecimal value;

        HotelDetailResponse hotel = null;

        if (quartoId != null) {
             hotel =  calculateTotalValueHotel(dtoTravelRequest, destiny.getDaily_value(), dtoTravelRequest.quarto_id());
            value = hotel.valor();
        }else {
           value = calculateDaysBaseValue(dtoTravelRequest, destiny.getDaily_value(), BigDecimal.ZERO);
        }

        Travel travel = new Travel(dtoTravelRequest.user_id(), destiny, dtoTravelRequest);
        TransactionCreationRequest request = new TransactionCreationRequest(dtoTravelRequest, travel, value);

        TransactionResponse response = transactionClient.transactionCreation(request);
        travel.setTransaction_id(response.Transaction_id());
        travelRepository.save(travel);
        travelRepository.flush();
        return new TravelResponse(dtoTravelRequest, destiny.getCountry().getName(), value, response.Transaction_id(),response.points(),hotel.nomeHotel());
    }

    public Travel consultTravel(Long travel_id) {
        Travel travel = travelRepository.findById(travel_id).orElseThrow(() -> new RuntimeException("Travel not found"));
        return travel;
    }




    public TravelCancelationResponse cancelTravel(Long travel_id) {
       Travel travel = travelRepository.findById(travel_id).orElseThrow(() -> new RuntimeException("Travel not found"));
       travel.setTravelStatus(TravelStatus.CANCELED);
       travelRepository.save(travel);
       return new TravelCancelationResponse(travel);
    }

    public HotelDetailResponse calculateTotalValueHotel(DTOTravelRequest travelRequest, BigDecimal destinyDayValue, Long quartoId) {
        HotelReservaRequest request = new HotelReservaRequest(travelRequest, quartoId);
        HotelDetailResponse response = hotelClient.reservaHotel(request);
        BigDecimal baseValue = calculateDaysBaseValue(travelRequest, destinyDayValue, response.valor());
        baseValue = applyRoundTripDiscount(baseValue, travelRequest);
        return new HotelDetailResponse(response, baseValue);

    }


    private BigDecimal calculateDaysBaseValue(DTOTravelRequest request, BigDecimal destinyDayValue, BigDecimal hotelValue) {
        Long days = ChronoUnit.DAYS.between(request.departureDate(), request.returnDate());
        BigDecimal travelValue = destinyDayValue.multiply(BigDecimal.valueOf(days));
        return travelValue.add(hotelValue);
    }

    private BigDecimal applyRoundTripDiscount(BigDecimal value, DTOTravelRequest request) {
        if (request.isRoundTrip()) {
            return value.multiply(BigDecimal.valueOf(0.9));
        }
        return value;
    }

}

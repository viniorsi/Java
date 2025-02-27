package com.br.TravelEase.Travel.Service.Travel;

import com.br.TravelEase.Travel.Domain.Destiny.Entity.Destiny;
import com.br.TravelEase.Travel.Domain.Destiny.Repository.DestinyRepository;
import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelRequest;
import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelResponse;
import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelResponseWithoutHotel;
import com.br.TravelEase.Travel.Domain.Travel.Entity.Travel;
import com.br.TravelEase.Travel.Domain.Travel.Repository.TravelRepository;
import com.br.TravelEase.Travel.feign.HotelClient;
import com.br.TravelEase.Travel.feign.request.HotelReservaRequest;
import com.br.TravelEase.Travel.feign.response.HotelDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

@Service
public class TravelService {

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    DestinyRepository destinyRepository;

    HotelClient hotelClient;

    @Autowired
    HotelsRepository hotelsRepository;

    @Autowired
    TravelHotelsRepository travelHotelsRepository;

    public DTOTravelResponse addTravel(DTOTravelRequest dtoTravelRequest){

        Destiny destiny = destinyRepository.findById(dtoTravelRequest.destiny_id())
                .orElseThrow(() -> new RuntimeException("Destiny not found"));
        Long hotels = dtoTravelRequest.hotel_id();

        Double value;
        int points;
//        TravelHotels travelHotels;
        Travel travel;
//        Transaction transaction;

// TODO se a viagem for com hotel, devera chamar o serviço de hotel se não segue o fluxo normal
        value = (hotels != null)
                ?
                calculateTotalValue(dtoTravelRequest, BigDecimal.valueOf(destiny.getDaily_value()),)
//                : transactionalService.getPriceWithoutHotel(dtoTravelRequest, destiny.getDaily_value());

//        points = transactionalService.generatePoints(value);
        travel = new Travel(dtoTravelRequest.user_id(), destiny, dtoTravelRequest);

//        if (hotels == null) {
            travelRepository.save(travel);
            travelRepository.flush();
            if (travel.getId() == null) {
                throw new RuntimeException("Travel ID was not generated");
            }
//          TODO chamar o serviço de transações
//            transaction = new Transaction(user, value, points,travel);
//            transactionalService.addTransaction(transaction,dtoTravelRequest,user,travel);
            return new DTOTravelResponseWithoutHotel(travel, transaction);
//        }

//        try {
//            travelRepository.save(travel);
//            transaction = new Transaction(user, value, points,travel);
//            transactionalService.addTransaction(transaction,dtoTravelRequest,user,travel);
//            travelHotels = associateHotelToTravel(travel.getId(), dtoTravelRequest, hotels);
//            return new DTOTravelResponseWithHotel(travelHotels, travel, transaction);
//        } catch (Exception e) {
//
//            throw new RuntimeException("Erro ao associar hotel a viagem" + e.getMessage());
//        }

    }

    public BigDecimal calculateTotalValue(DTOTravelRequest travelRequest,BigDecimal destinyDayValue, Long quartoId) {
        HotelReservaRequest request = new HotelReservaRequest(travelRequest,quartoId);
        HotelDetailResponse response = hotelClient.reservaHotel(request);
        BigDecimal baseValue = calculateDaysBaseValue(travelRequest,destinyDayValue,response.valor());
        baseValue = applyRoundTripDiscount(baseValue, travelRequest);
        return baseValue;
    }

    private BigDecimal calculateDaysBaseValue(DTOTravelRequest request, BigDecimal destinyDayValue,BigDecimal hotelValue) {
        Long days = ChronoUnit.DAYS.between(request.departureDate(), request.returnDate());
        BigDecimal travelValue = destinyDayValue.multiply(BigDecimal.valueOf(days));
        return travelValue.add(hotelValue);
    }

//    private BigDecimal addPetFees(BigDecimal value, DTOTravelRequest request, HotelDetailResponse hotel) {
//        if (request.pet() > 0) {
//            BigDecimal petFee = hotel.precoPorNoite()
//                    .multiply(BigDecimal.valueOf(request.pet()))
//                    .multiply(BigDecimal.valueOf(3));
//            return value.add(petFee);
//        }
//        return value;
//    }
//
//    private BigDecimal addKidsDiscount(BigDecimal value, DTOTravelRequest request, HotelDetailResponse hotel) {
//        if (request.kids_count() >= 2) {
//            BigDecimal kidsAddition = hotel.precoPorNoite()
//                    .multiply(BigDecimal.valueOf(request.kids_count()))
//                    .divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
//            return value.add(kidsAddition);
//        }
//        return value;
//    }

    private BigDecimal applyRoundTripDiscount(BigDecimal value, DTOTravelRequest request) {
        if (request.isRoundTrip()) {
            return value.multiply(BigDecimal.valueOf(0.9));
        }
        return value;
    }



//    public TravelHotels associateHotelToTravel(Long travelId, DTOTravelRequest travelRequest, Hotels hotel) {
//        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new RuntimeException("Travel not found"));
//
//        TravelHotels travelHotels = new TravelHotels(
//                travel,
//                hotel,
//                travelRequest.adults_count(),
//                travelRequest.kids_count(),
//                travelRequest.pet(),
//                travel.getDepartureDate(),
//                travel.getReturn_date()
//        );
//
//        travelHotelsRepository.save(travelHotels);
//
//        return travelHotels;
//    }


}

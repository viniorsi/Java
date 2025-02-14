package com.br.TravelEase.Travel.Service.Travel;

import com.br.TravelEase.Travel.Domain.Destiny.Entity.Destiny;
import com.br.TravelEase.Travel.Domain.Destiny.Repository.DestinyRepository;
import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelRequest;
import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelResponse;
import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelResponseWithoutHotel;
import com.br.TravelEase.Travel.Domain.Travel.Entity.Travel;
import com.br.TravelEase.Travel.Domain.Travel.Repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelService {

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    DestinyRepository destinyRepository;

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


//                transactionalService.getPriceWithHotel(dtoTravelRequest, destiny.getDaily_value(), hotels)
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

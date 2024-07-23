package com.viniorsi.TravelEase.Service.Travel;

import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import com.viniorsi.TravelEase.Domain.Destiny.Repository.DestinyRepository;
import com.viniorsi.TravelEase.Domain.Hotels.Entity.Hotels;
import com.viniorsi.TravelEase.Domain.Hotels.Repository.HotelsRepository;
import com.viniorsi.TravelEase.Domain.Ticket.DTO.DTOTicketPerson;
import com.viniorsi.TravelEase.Domain.Transactional.Entity.Transactional;
import com.viniorsi.TravelEase.Domain.Transactional.Repository.TransactionalRepository;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelRequest;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelResponse;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelResponseWithHotel;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelResponseWithoutHotel;
import com.viniorsi.TravelEase.Domain.Travel.Entity.Travel;
import com.viniorsi.TravelEase.Domain.Travel.Repository.TravelRepository;
import com.viniorsi.TravelEase.Domain.TravelHotels.Entity.TravelHotels;
import com.viniorsi.TravelEase.Domain.TravelHotels.Repository.TravelHotelsRepository;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Repository.User.UserRespository;
import com.viniorsi.TravelEase.Service.Transactional.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TravelService {

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    UserRespository userRespository;

    @Autowired
    DestinyRepository destinyRepository;

    @Autowired
    HotelsRepository hotelsRepository;

    @Autowired
    TravelHotelsRepository travelHotelsRepository;

    @Autowired
    TransactionalService transactionalService;



    public DTOTravelResponse addTravel(DTOTravelRequest dtoTravelRequest) {

        User user = (userRespository.getReferenceByCpf(dtoTravelRequest.cpfUser()));
        Destiny destiny = destinyRepository.findById(dtoTravelRequest.destiny_id())
                .orElseThrow(() -> new RuntimeException("Destiny not found"));
        Hotels hotels = hotelsRepository.findById(dtoTravelRequest.hotel_id()).orElse(null);

        Double value;
        int points;
        TravelHotels travelHotels;
        Travel travel;
        Transactional transaction;


        value = (hotels != null)
                ? transactionalService.getPriceWithHotel(dtoTravelRequest, destiny.getDaily_value(), hotels)
                : transactionalService.getPriceWithoutHotel(dtoTravelRequest, destiny.getDaily_value());

        points = transactionalService.generatePoints(value);
        travel = new Travel(user, destiny, dtoTravelRequest);

        if (hotels == null) {
            travelRepository.save(travel);
            travelRepository.flush();
            if (travel.getId() == null) {
                throw new RuntimeException("Travel ID was not generated");
            }

            transaction = new Transactional(user, value, points,travel);
            transactionalService.addTransaction(transaction,dtoTravelRequest,user,travel);
            return new DTOTravelResponseWithoutHotel(travel, transaction);
        }

        try {
            travelRepository.save(travel);
            transaction = new Transactional(user, value, points,travel);
            transactionalService.addTransaction(transaction,dtoTravelRequest,user,travel);
            travelHotels = associateHotelToTravel(travel.getId(), dtoTravelRequest, hotels);
            return new DTOTravelResponseWithHotel(travelHotels, travel, transaction);
        } catch (Exception e) {

            throw new RuntimeException("Erro ao associar hotel a viagem" + e.getMessage());
        }

    }



    public TravelHotels associateHotelToTravel(Long travelId, DTOTravelRequest travelRequest, Hotels hotel) {
        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new RuntimeException("Travel not found"));

        TravelHotels travelHotels = new TravelHotels(
                travel,
                hotel,
                travelRequest.adults_count(),
                travelRequest.kids_count(),
                travelRequest.pet(),
                travel.getDepartureDate(),
                travel.getReturn_date()
        );

        travelHotelsRepository.save(travelHotels);

        return travelHotels;
    }


}

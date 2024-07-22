package com.viniorsi.TravelEase.Service.Travel;

import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import com.viniorsi.TravelEase.Domain.Destiny.Repository.DestinyRepository;
import com.viniorsi.TravelEase.Domain.Hotels.Entity.Hotels;
import com.viniorsi.TravelEase.Domain.Hotels.Repository.HotelsRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

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
    TransactionalRepository transactionalRepository;



    public DTOTravelResponse addTravel(DTOTravelRequest dtoTravelRequest) {

        User user = (userRespository.getReferenceByCpf(dtoTravelRequest.cpfUser()));
        Destiny destiny = destinyRepository.findById(dtoTravelRequest.destiny_id())
                .orElseThrow(() -> new RuntimeException("Destiny not found"));
        Hotels hotels = hotelsRepository.findById(dtoTravelRequest.hotel_id()).orElse(null);

        Double value;
        int points;
        TravelHotels travelHotels;
        Travel travel;
        Transactional transactional;


        value = (hotels != null)
                ? getPriceWithHotel(dtoTravelRequest, destiny.getDaily_value(), hotels)
                : getPriceWithoutHotel(dtoTravelRequest, destiny.getDaily_value());
        points = generatePoints(value);
        travel = new Travel(user, destiny, dtoTravelRequest);

        if (hotels == null) {
            travelRepository.save(travel);
            travelRepository.flush();
            if (travel.getId() == null) {
                throw new RuntimeException("Travel ID was not generated");
            }
            transactional = new Transactional(user, value, points,travel);
            transactionalRepository.save(transactional);
            return new DTOTravelResponseWithoutHotel(travel, transactional);
        }

        try {
            travelRepository.save(travel);
            transactional = new Transactional(user, value, points,travel);
            transactionalRepository.save(transactional);
            travelHotels = associateHotelToTravel(travel.getId(), dtoTravelRequest, hotels);
            return new DTOTravelResponseWithHotel(travelHotels, travel, transactional);
        } catch (Exception e) {

            throw new RuntimeException("Erro ao associar hotel a viagem" + e.getMessage());
        }

    }

    private int generatePoints(Double value) {
        value = value * 0.2;
        return value.intValue();
    }

    public Double getPriceWithHotel(DTOTravelRequest travelRequest, Double dailyValueDestiny, Hotels hotel) {

        var Days = ChronoUnit.DAYS.between(travelRequest.departureDate(), travelRequest.returnDate());
        var value = (dailyValueDestiny * Days) + hotel.getValue() * travelRequest.adults_count();
        if (travelRequest.pet() > 0) {
            value += hotel.getValue() * travelRequest.pet() * 3;
        }
        if (travelRequest.kids_count() >= 2) {
            value += (hotel.getValue() * travelRequest.kids_count()) / 2;
        }
        if (travelRequest.isRoundTrip()) {
            value -= -value * 0.1;
        }
        return value;

    }

    public Double getPriceWithoutHotel(DTOTravelRequest travelRequest, Double dailyValueDestiny) {

        var Days = ChronoUnit.DAYS.between(travelRequest.departureDate(), travelRequest.returnDate());
        var value = (dailyValueDestiny * Days) * travelRequest.ticketscount();
        if (travelRequest.pet() > 0) {
            value += 100 * travelRequest.pet();
        }
        if (travelRequest.isRoundTrip()) {
            value -= value * 0.1;
        }
        return value;

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

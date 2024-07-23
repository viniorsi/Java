package com.viniorsi.TravelEase.Service.Transactional;

import com.viniorsi.TravelEase.Domain.Hotels.Entity.Hotels;
import com.viniorsi.TravelEase.Domain.Transactional.Entity.Transactional;
import com.viniorsi.TravelEase.Domain.Transactional.Repository.TransactionalRepository;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelRequest;
import com.viniorsi.TravelEase.Service.Ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class TransactionalService {

    @Autowired
    TransactionalRepository transactionalRepository;

    @Autowired
    TicketService ticketService;

    public void addTransaction(Transactional transaction,DTOTravelRequest dtoTravelRequest){

        transactionalRepository.save(transaction);

        ticketService.ticketCreation(dtoTravelRequest);

    }

    public int generatePoints(Double value) {
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


}

//package com.br.TravelEase.Travel.Domain.Travel.DTO;
//
//import com.viniorsi.TravelEase.Domain.Transaction.Entity.Transaction;
//import com.viniorsi.TravelEase.Domain.Travel.Entity.Travel;
//import com.viniorsi.TravelEase.Domain.TravelHotels.Entity.TravelHotels;
//
//import java.time.LocalDateTime;
//
//public record DTOTravelResponseWithHotel(
//        String cpf,
//        String destinationName,
//        String departureLocation,
//        LocalDateTime departureDate,
//        LocalDateTime arrivalDate,
//        boolean roundTrip,
//        int ticketCount,
//        int points,
//        double value,
//        String hotelName,
//        int adultCount,
//        int childCount,
//        boolean pet,
//        Long transactional_id,
//        String stripe_transaction_id
//) implements DTOTravelResponse {
//
//
//    public DTOTravelResponseWithHotel(TravelHotels travelHotels, Travel travel, Transaction transaction) {
//        this(travel.getUser().getCpf(),
//                travel.getDestiny().getCountry().getName(),
//                travel.getDepartureLocation().getName(),
//                travel.getDepartureDate(),
//                travel.getReturn_date(),
//                travel.isRoundTrip(),
//                travel.getTicketsCount(),
//                transaction.getPoints(),
//                transaction.getValue(),
//                travelHotels.getHotels().getName(),
//                travelHotels.getAdult_count(),
//                travelHotels.getKids_count(),
//                travelHotels.getHotels().isPet(),
//                transaction.getId(),
//                transaction.getStripe_transaction_id());
//    }
//}

//package com.br.TravelEase.Travel.Domain.Travel.DTO;
//
//import java.time.LocalDateTime;
//
//public sealed interface DTOTravelResponse permits DTOTravelResponseWithHotel, DTOTravelResponseWithoutHotel {
//    String cpf();
//    String destinationName();
//    String departureLocation();
//    LocalDateTime departureDate();
//    LocalDateTime arrivalDate();
//    boolean roundTrip();
//    int ticketCount();
//    int points();
//    double value();
//}
//
////@Getter
////public abstract class DTOTravelResponse{
////
////    private final String cpf;
////    private final String destinationName;
////    private final LocalDateTime departureDate;
////    private final LocalDateTime arrivalDate;
////    private final boolean roundTrip;
////    private final int ticketCount;
////    private final int points;
////    private final double value;
////
////    protected DTOTravelResponse(Travel travel) {
////        this.cpf = travel.getUser().getCpf();
////        this.destinationName = travel.getDestiny().getName();
////        this.departureDate = travel.getDepartureDate();
////        this.arrivalDate = travel.getReturn_date();
////        this.roundTrip =  travel.isRoundTrip();
////        this.ticketCount = travel.getTicketsCount();
////        this.points = travel.getPoints();
////        this.value = travel.getValue();
////    }
////
////}

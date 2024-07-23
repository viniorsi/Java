package com.viniorsi.TravelEase.Service.Ticket;

import com.viniorsi.TravelEase.Domain.Ticket.DTO.DTOTicketPerson;
import com.viniorsi.TravelEase.Domain.Ticket.Entity.Ticket;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {


    public void ticketCreation(DTOTravelRequest dtoTravelRequest){

     List<DTOTicketPerson> ticketsList = dtoTravelRequest.ticketsList();

     for (DTOTicketPerson tickets : ticketsList){



     }

    }


}

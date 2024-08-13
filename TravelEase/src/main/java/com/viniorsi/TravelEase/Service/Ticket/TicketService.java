package com.viniorsi.TravelEase.Service.Ticket;

import com.google.zxing.WriterException;
import com.viniorsi.TravelEase.Domain.Airplane.Seat.Entity.Seat;
import com.viniorsi.TravelEase.Domain.Airplane.Seat.Repository.SeatRepository;
import com.viniorsi.TravelEase.Domain.Ticket.DTO.DTOTicketPerson;
import com.viniorsi.TravelEase.Domain.Ticket.Entity.Ticket;
import com.viniorsi.TravelEase.Domain.Ticket.Enums.StatusTicketUsageEnum;
import com.viniorsi.TravelEase.Domain.Ticket.TicketRepository;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelRequest;
import com.viniorsi.TravelEase.Domain.Travel.Entity.Travel;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Service.QRCode.GenerateQrcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    TicketRepository ticketRepository;


    public List<Ticket> ticketCreation(DTOTravelRequest dtoTravelRequest, User user, Travel travel){

        Ticket ticket;

        List<Ticket> createdTickets = new ArrayList<>();

     List<DTOTicketPerson> ticketsList = dtoTravelRequest.ticketsList();

     for (DTOTicketPerson tickets : ticketsList){

         Seat seat = seatRepository.getReferenceById(tickets.seat_id());

         String qrCodeData = "Assento -" + seat.getSeat_number() + "Aviao -" + seat.getAirplane() + "Nome -" + user.getName() + "Data de emissão - " + LocalDateTime.now();
         String qrcode = "";
         String qrcodeHash = "";
         try {
             qrcode = GenerateQrcode.generateQRCode(qrCodeData, 200, 200);
             qrcodeHash = GenerateQrcode.generateHash(qrcode);
         } catch (WriterException | IOException | NoSuchAlgorithmException e) {
             e.printStackTrace();
         }

        ticket = new Ticket(user,tickets.name(),tickets.cpf(),tickets.birthday(),travel,seat,qrcode,qrcodeHash);

        ticketRepository.save(ticket);
         createdTickets.add(ticket);


     }
        return createdTickets;
    }

    public void setStatusToActive(List<Ticket> tickets){

        for(Ticket ticket : tickets){
            ticket.setStatus(StatusTicketUsageEnum.A);
            ticketRepository.save(ticket);

        }

    }




}

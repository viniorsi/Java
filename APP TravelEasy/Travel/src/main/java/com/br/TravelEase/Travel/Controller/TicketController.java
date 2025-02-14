package com.br.TravelEase.Travel.Controller;


import com.br.TravelEase.Travel.Domain.Ticket.DTO.DTOGetTickets;
import com.br.TravelEase.Travel.Domain.Ticket.DTO.DTOTravelTicket;
import com.br.TravelEase.Travel.Service.Ticket.TicketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
@SecurityRequirement(name = "bearer-key")

public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/gettickets")
    public ResponseEntity<Page<DTOTravelTicket>> listTickets(
            @RequestBody DTOGetTickets dtoGetTickets,
            @PageableDefault(size = 10) Pageable pagination) {

        Page<DTOTravelTicket> tickets = ticketService.listTicketsByTransaction(dtoGetTickets.user_id(),dtoGetTickets.travel_id(), pagination);
        return ResponseEntity.ok(tickets);
    }

//



}

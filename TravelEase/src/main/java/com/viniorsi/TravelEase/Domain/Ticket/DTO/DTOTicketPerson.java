package com.viniorsi.TravelEase.Domain.Ticket.DTO;

import com.viniorsi.TravelEase.Domain.User.Entity.User;

import java.time.LocalDateTime;

public record DTOTicketPerson(
        Long user_id,
         String name,
         String cpf,
         LocalDateTime birthday
) {

}

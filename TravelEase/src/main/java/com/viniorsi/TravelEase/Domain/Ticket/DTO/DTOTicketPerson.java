package com.viniorsi.TravelEase.Domain.Ticket.DTO;

import java.time.LocalDate;

public record DTOTicketPerson(
        Long user_id,
        String name,
        String cpf,
        LocalDate birthday,
        Long seat_id
) {

}

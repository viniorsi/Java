package com.viniorsi.TravelEase.Domain.Ticket.DTO;

import com.viniorsi.TravelEase.Domain.Ticket.Entity.Ticket;
import com.viniorsi.TravelEase.Domain.Ticket.Enums.StatusTicketUsageEnum;
import com.viniorsi.TravelEase.Domain.Transaction.Entity.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DTOTravelTicket(
        String Cpf,
        String name,
        LocalDate birthday,
        String qrcode,
        LocalDateTime emissionDate,
        StatusTicketUsageEnum status,
        LocalDateTime usageDate

) {

    public DTOTravelTicket(Ticket ticket) {
        this(
    ticket.getCpf(), ticket.getName(), ticket.getBirthday(), ticket.getQrcode(), ticket.getEmission_date(), ticket.getStatus(), ticket.getUsage_date()
        );

    }


}

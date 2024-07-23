package com.viniorsi.TravelEase.Domain.Ticket;

import com.viniorsi.TravelEase.Domain.Ticket.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}

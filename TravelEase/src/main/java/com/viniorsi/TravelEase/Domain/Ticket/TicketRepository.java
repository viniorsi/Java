package com.viniorsi.TravelEase.Domain.Ticket;

import com.viniorsi.TravelEase.Domain.Ticket.DTO.DTOTravelTicket;
import com.viniorsi.TravelEase.Domain.Ticket.Entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("SELECT t FROM Ticket t WHERE t.travel_id.id = :travelId")
    List<Ticket> findAllByTravelId(@Param("travelId") Long travelId);

    @Query("SELECT t FROM Ticket t WHERE t.user_id.id = :userId AND t.travel_id.id= :travelId")
    Page<Ticket> findAllByUserIdAndTravelId(@Param("userId") Long userId, @Param("travelId") Long travelId, Pageable pageable);

}

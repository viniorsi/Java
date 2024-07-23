package com.viniorsi.TravelEase.Domain.Airplane.Seat.Repository;

import com.viniorsi.TravelEase.Domain.Airplane.Seat.Entity.Seat;
import com.viniorsi.TravelEase.Domain.Hotels.Entity.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    @Override
    Seat getReferenceById(Long id);

}

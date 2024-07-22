package com.viniorsi.TravelEase.Domain.TravelHotels.Repository;

import com.viniorsi.TravelEase.Domain.TravelHotels.Entity.TravelHotels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelHotelsRepository extends JpaRepository<TravelHotels, Long> {
}

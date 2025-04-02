package com.br.TravelEase.Travel.Domain.Travel.Repository;

import com.br.TravelEase.Travel.Domain.Travel.Entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}

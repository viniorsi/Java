package com.viniorsi.TravelEase.Domain.Airplane.Airplane.Repository;

import com.viniorsi.TravelEase.Domain.Airplane.Airplane.Entity.Airplane;
import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Long> {

    @EntityGraph(attributePaths = {"airplanes"})
    Page<Airplane> findAll(Pageable pageable);
}

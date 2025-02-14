package com.br.TravelEase.Travel.Domain.Destiny.Repository;

import com.br.TravelEase.Travel.Domain.Destiny.Entity.Destiny;
import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinyRepository extends JpaRepository<Destiny, Long> {

    @EntityGraph(attributePaths = {"airplanes"})
    Page<Destiny> findAll(Pageable pageable);
}

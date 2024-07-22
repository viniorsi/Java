package com.viniorsi.TravelEase.Domain.Hotels.Repository;

import com.viniorsi.TravelEase.Domain.Hotels.Entity.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelsRepository extends JpaRepository<Hotels, Long> {

    @Override
    Hotels getReferenceById(Long id);

}

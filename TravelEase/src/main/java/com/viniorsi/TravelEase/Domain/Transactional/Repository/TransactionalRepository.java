package com.viniorsi.TravelEase.Domain.Transactional.Repository;

import com.viniorsi.TravelEase.Domain.Transactional.Entity.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionalRepository extends JpaRepository<Transactional, Long> {
}

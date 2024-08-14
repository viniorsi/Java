package com.viniorsi.TravelEase.Domain.Feedback.Repository;

import com.viniorsi.TravelEase.Domain.Feedback.Entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

    Page<Feedback> findAllByDestiny_Id(Long destinyId, Pageable pageable);

}

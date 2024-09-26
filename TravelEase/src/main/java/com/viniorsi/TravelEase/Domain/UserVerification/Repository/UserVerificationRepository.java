package com.viniorsi.TravelEase.Domain.UserVerification.Repository;

import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.UserVerification.Entity.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long>{

    UserVerification findByUser(User user);
}

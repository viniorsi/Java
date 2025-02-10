package com.br.TravelEasy.Auth.Feing.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long>{

    UserVerification findByUser(User user);
}

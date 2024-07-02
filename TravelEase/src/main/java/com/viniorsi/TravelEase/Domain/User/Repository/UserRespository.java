package com.viniorsi.TravelEase.Domain.User.Repository;

import com.viniorsi.TravelEase.Domain.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UserRespository extends JpaRepository<User, Long> {


    Optional<User> findByCpf(String cpf);
}

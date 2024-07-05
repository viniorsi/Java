package com.viniorsi.TravelEase.Repository.User;

import com.viniorsi.TravelEase.Domain.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UserRespository extends JpaRepository<User, Long> {


    User findByCpf(String cpf);

//    Object findByEmail(String email);
}

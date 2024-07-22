package com.viniorsi.TravelEase.Repository.User;

import com.viniorsi.TravelEase.Domain.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRespository extends JpaRepository<User, Long> {

    User getReferenceByCpf(String cpf);
}

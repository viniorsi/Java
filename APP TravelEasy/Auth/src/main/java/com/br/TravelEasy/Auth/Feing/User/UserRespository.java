package com.br.TravelEasy.Auth.Feing.User;


import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRespository extends JpaRepository<User, Long> {

    User getReferenceByCpf(String cpf);
}

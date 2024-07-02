package com.viniorsi.TravelEase.Domain.User.Service;

import com.viniorsi.TravelEase.Domain.User.Repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRespository repository;


    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new UsernameNotFoundException("Dados inválidos"));
    }
}

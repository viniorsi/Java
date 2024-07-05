package com.viniorsi.TravelEase.Service.Auth;

import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserLogin;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Infra.Security.DTOTokenJWT;
import com.viniorsi.TravelEase.Infra.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    public DTOTokenJWT Login(DTOUserLogin dtoUsuarioLogin) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dtoUsuarioLogin.login(), dtoUsuarioLogin.senha());
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
            return new DTOTokenJWT(tokenJWT);
        } catch (Exception e) {
            throw new RuntimeException("Login ou senha inválidos");
        }
    }
}

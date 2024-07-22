package com.viniorsi.TravelEase.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserLogin;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Infra.Security.DTOTokenJWT;
import com.viniorsi.TravelEase.Service.Auth.LoginService;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginService loginService;

    @Mock
    private User user;

    @Mock
    private DTOTokenJWT dtoTokenJWT;

    @Test
    public void testSuccessfulLogin() throws Exception {

        DTOUserLogin loginData = new DTOUserLogin("user", "password");
        when(loginService.Login(loginData)).thenReturn(dtoTokenJWT);


        ResponseEntity<Object> response = loginController.singIn(loginData);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dtoTokenJWT, response.getBody());
    }

}
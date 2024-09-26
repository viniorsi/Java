package com.viniorsi.TravelEase.Controller;


import com.viniorsi.TravelEase.Domain.Address.DTO.DTOAddress;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserDetails;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserRegister;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Enums.StatusEnum;
import com.viniorsi.TravelEase.Domain.User.Enums.Verificationtype;
import com.viniorsi.TravelEase.Domain.UserVerification.DTO.DTOUserVerificationStatusRequest;
import com.viniorsi.TravelEase.Service.User.UserRegisterService;
import com.viniorsi.TravelEase.Service.User.UserVerificationCodeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    private UserRegisterService userRegisterService;

    @Mock
    private UserVerificationCodeService userVerificationCodeService;

    @Mock
    UriComponentsBuilder uriBuilder;



    @Test
    public void testSuccessfulCreationSend() throws Exception {
        UriComponentsBuilder realUriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");
        when(uriBuilder.path("/user/{id}")).thenReturn(realUriBuilder);
        DTOUserRegister userData = new DTOUserRegister(
                "45822502890",
                "User",
                "000000000",
                "00",
                "teste@teste.com",
                "password",
                LocalDate.now(),
                addressData(),
                Verificationtype.EMAIL);

        DTOUserDetails dtoUserDetails = new DTOUserDetails(
                "User",
                "teste@teste.com",
                "45822502890",
                "00",
                "000000000",
                addressData().state(),
                LocalDate.now(),
                LocalDate.now(),
                "Pendente",
                "123456");

        ResponseEntity<Object> response = userController.createUser(userData, uriBuilder);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testSuccessfulCodeVerification() throws Exception {
        User user = new User();
        user.setCpf("45822502890");
        user.setStatus(StatusEnum.A);
        DTOUserVerificationStatusRequest verificationRequest = new DTOUserVerificationStatusRequest(
                "45822502890", "123456");
        when(userVerificationCodeService.codeVerification(verificationRequest.cpf(), verificationRequest.verificationCode())).thenReturn(user);
        ResponseEntity<Object> response = userController.codeVerifications(verificationRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }



    private DTOAddress addressData() {
        return new DTOAddress(
                "Rua XPTO",
                "Bairro",
                "00000000",
                "Brasília",
                "DF",
                "São Paulo",
                "SP"
        );
    }
}




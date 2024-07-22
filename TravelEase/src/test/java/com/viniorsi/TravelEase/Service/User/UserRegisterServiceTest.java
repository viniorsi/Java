package com.viniorsi.TravelEase.Service.User;

import com.viniorsi.TravelEase.Domain.Address.DTO.DTOAddress;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserRegister;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Enums.Verificationtype;
import com.viniorsi.TravelEase.Domain.UserVerification.Entity.UserVerification;
import com.viniorsi.TravelEase.Repository.User.UserRespository;
import com.viniorsi.TravelEase.Repository.UserVerification.UserVerificationRepository;
import com.viniorsi.TravelEase.Repository.html.HtmlTemplateRepository;
import com.viniorsi.TravelEase.Service.Email.EmailPublisher;
import com.viniorsi.TravelEase.Service.SMS.SMSService;
import com.viniorsi.TravelEase.Service.html.HtmlTemplateService;
import com.viniorsi.TravelEase.Utils.EncryptDecrypt;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRegisterServiceTest {

    @InjectMocks
    private UserRegisterService userRegisterService;

    @Mock
    UserRespository userRespository;

    @Mock
    private EncryptDecrypt encryptDecrypt;

    @Mock
    HtmlTemplateService htmlTemplateService;

    @Mock
    HtmlTemplateRepository htmlTemplateRepository;

    @Mock
    private UserVerificationRepository userVerificationRepository;

    @Mock
    private EmailPublisher emailPublisher;

    @Mock
    SMSService smsService;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    void whenUserIsSuccessfullyCreatedSendEmail() throws Exception {
        when(encryptDecrypt.passwordEncoder()).thenReturn(passwordEncoder);
        when(htmlTemplateService.getTemplateContent(2L)).thenReturn("<html>Seu código de verificação é: 1 2 3 4 5 6</html>");

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
        User user = new User(userData);

        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRespository.save(any())).thenReturn(new User(userData));
        when(userVerificationRepository.save(any())).thenReturn(new UserVerification(user));

        var result = userRegisterService.createUser(userData);

        assertEquals(userData.email(), result.email());
    }

    @Test
    void whenUserIsSuccessfullyCreatedSendSms() throws Exception {
        when(encryptDecrypt.passwordEncoder()).thenReturn(passwordEncoder);
        DTOUserRegister userData = new DTOUserRegister(
                "45822502890",
                "User",
                "000000000",
                "00",
                "teste@teste.com",
                "password",
                LocalDate.now(),
                addressData(),
                Verificationtype.SMS);
        User user = new User(userData);

        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRespository.save(any())).thenReturn(new User(userData));
        when(userVerificationRepository.save(any())).thenReturn(new UserVerification(user));

        var result = userRegisterService.createUser(userData);

        assertEquals(userData.email(), result.email());
    }

    @Test
    void ErrorCreatingExistingUser() throws Exception {
        DTOUserRegister userData = new DTOUserRegister(
                "45822502890",
                "User",
                "000000000",
                "00",
                "teste@teste.com",
                "password",
                LocalDate.now(),
                addressData(),
                Verificationtype.SMS);
        User user = new User(userData);

        when(userRespository.getReferenceByCpf(user.getCpf())).thenReturn(user);
//        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
//        when(userRespository.save(any())).thenReturn(new User(userData));
//        when(userVerificationRepository.save(any())).thenReturn(new UserVerification(user));

        Exception exception = assertThrows(Exception.class, () -> userRegisterService.createUser(userData));

        assertEquals("Usuario ja existente", exception.getMessage());


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
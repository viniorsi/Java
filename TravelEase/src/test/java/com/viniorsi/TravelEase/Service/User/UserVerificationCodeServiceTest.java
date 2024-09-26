package com.viniorsi.TravelEase.Service.User;


import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Enums.StatusEnum;
import com.viniorsi.TravelEase.Domain.UserVerification.Entity.UserVerification;
import com.viniorsi.TravelEase.Domain.User.Repository.UserRespository;
import com.viniorsi.TravelEase.Domain.UserVerification.Repository.UserVerificationRepository;
import com.viniorsi.TravelEase.Utils.EncryptDecrypt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserVerificationCodeServiceTest {


    @InjectMocks
    private UserVerificationCodeService userVerificationCodeService;

    @Mock
    private UserRespository userRepository;

    @Mock
    private UserVerificationRepository userVerificationRepository;

    @Mock
    private EncryptDecrypt encryptDecrypt;






    @Test
    @DisplayName("When verification code is valid, user status is set to ACTIVE")
    void whenVerificationCodeIsValid_userStatusIsSetActive() throws Exception {
        User mockUser = new User();
        mockUser.setCpf("12345678900");
        mockUser.setStatus(StatusEnum.P);
        UserVerification mockUserVerification = new UserVerification();
        mockUserVerification.setUser(mockUser);
        String encript = EncryptDecrypt.encrypt("123456", EncryptDecrypt.getSecretKey());
        mockUserVerification.setVerificationCode(encript);
        mockUserVerification.setExpirationDate(LocalDateTime.now().plusHours(2));

        when(userRepository.getReferenceByCpf("12345678900")).thenReturn(mockUser);
        when(userVerificationRepository.findByUser(mockUser)).thenReturn(mockUserVerification);

        User result = userVerificationCodeService.codeVerification("12345678900", "123456");

        assertEquals(StatusEnum.A, result.getStatus());
    }


}
package com.br.TravelEasy.Auth.Feing.response;

import com.br.TravelEasy.Auth.Feing.User.User;
import com.br.TravelEasy.Auth.Feing.User.UserVerification;


import java.time.LocalDate;

public record DTOUserDetails(

        String name,
        String email,
        String cpf,
        String ddd,
        String tel,
        String state,
        LocalDate creation_date,
        LocalDate birthday,
        String status,
        String verificationCode

) {
    public DTOUserDetails(User user, UserVerification userVerification) {
        this(
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getDdd(),
                user.getTel(),
                user.getAddress().getState(),
                user.getCreation_date(),
                user.getBirthday(),
                user.getStatus().getDescricao(),
                userVerification.getVerificationCode());
    }



}

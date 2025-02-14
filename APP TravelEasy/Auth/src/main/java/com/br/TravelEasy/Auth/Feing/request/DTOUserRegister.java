package com.br.TravelEasy.Auth.Feing.request;


import com.br.TravelEasy.Auth.Feing.User.Address.DTO.DTOAddress;
import com.br.TravelEasy.Auth.Feing.enums.Verificationtype;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record DTOUserRegister(

        @NotBlank @CPF
        String cpf,
        @NotBlank
        String name,
        @NotBlank
        String ddd,
        @NotBlank
        String tel,
        @NotBlank @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        LocalDate birthday,
        @NotNull @Valid
        DTOAddress address,
        @NotNull
        Verificationtype verificationType


) {
}

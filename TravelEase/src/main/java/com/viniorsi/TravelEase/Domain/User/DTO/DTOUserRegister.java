package com.viniorsi.TravelEase.Domain.User.DTO;


import com.viniorsi.TravelEase.Domain.Address.DTO.DTOAddress;
import com.viniorsi.TravelEase.Domain.User.Enums.Verificationtype;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record DTOUserRegister(

        @NotBlank @CPF
        String cpf,
        @NotBlank
        String name,
        @NotBlank
        String tel,
        @NotBlank
        String ddd,
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

package com.br.TravelEasy.Auth.Feing.request;

public record DTOUserVerificationStatusRequest(
        String cpf,
        String verificationCode
) {
}

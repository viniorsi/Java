package com.viniorsi.TravelEase.Domain.UserVerification.DTO;

public record DTOUserVerificationStatusRequest(
        String cpf,
        String verificationCode
) {
}

package com.viniorsi.TravelEase.Domain.User.DTO;

public record DTOUserVerificationStatusRequest(
        String cpf,
        String uuid
) {
}

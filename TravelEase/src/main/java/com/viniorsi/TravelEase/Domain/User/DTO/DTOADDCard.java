package com.viniorsi.TravelEase.Domain.User.DTO;

import jakarta.validation.constraints.NotBlank;

public record DTOADDCard(
        @NotBlank String cpf,
        @NotBlank String tokenId
) {

}

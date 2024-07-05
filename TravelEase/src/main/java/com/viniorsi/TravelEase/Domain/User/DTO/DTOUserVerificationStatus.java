package com.viniorsi.TravelEase.Domain.User.DTO;

import com.viniorsi.TravelEase.Domain.User.Entity.User;

public record DTOUserVerificationStatus(
        String cpf,
        String name,
        String email,
        String status

) {
    public DTOUserVerificationStatus(User user) {
        this(
                user.getCpf(),
                user.getName(),
                user.getEmail(),
                user.getStatus().getDescricao());
    }


}

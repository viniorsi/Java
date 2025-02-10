package com.br.TravelEasy.Auth.Feing.response;

import com.br.TravelEasy.Auth.Feing.User.User;

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

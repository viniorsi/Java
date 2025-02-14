package com.br.TravelEase.EmailSmsSender.Email;

public record EmailSmsRequest(
        String email,
        String verificiationCode,
        String ddd,
        String tel,
        VerificationType verificationtype
) {
}


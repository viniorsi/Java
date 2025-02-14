package com.br.TravelEasy.Auth.Feing.Email;

import com.br.TravelEasy.Auth.Feing.User.User;
import com.br.TravelEasy.Auth.Feing.User.UserVerification;
import com.br.TravelEasy.Auth.Feing.enums.Verificationtype;

public record EmailSmsRequest(
        String email,
        String verificiationCode,
        String ddd,
        String tel,
        Verificationtype verificationtype
) {
}

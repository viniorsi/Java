package com.viniorsi.TravelEase.Service.User;

import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Enums.StatusEnum;
import com.viniorsi.TravelEase.Domain.UserVerification.Entity.UserVerification;
import com.viniorsi.TravelEase.Repository.User.UserRespository;
import com.viniorsi.TravelEase.Repository.UserVerification.UserVerificationRepository;
import com.viniorsi.TravelEase.Utils.EncryptDecrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVerificationCodeService {
    @Autowired
    private UserRespository userRepository;

    @Autowired
    private UserVerificationRepository userVerificationRepository;

    public User codeVerification(String cpf, String verificationCode) throws Exception {

        User user = userRepository.getReferenceByCpf(cpf);

        if (user == null) {
            throw new Exception("Usuario não existe");
        }

        try {
            var secret = EncryptDecrypt.getSecretKey();
            UserVerification userVerification = userVerificationRepository.findByUser(user);
            if (EncryptDecrypt.decrypt(userVerification.getVerificationCode(), secret).matches(verificationCode) && userVerification.getExpirationDate().isBefore(userVerification.getExpirationDate().plusHours(2))) {
                user.setStatus(StatusEnum.A);
                userRepository.save(user);
                return user;
            } else {
                throw new Exception("Codigo de verificação invalido ou expirado");
            }

        } catch (Exception e) {
            throw new Exception("Usuario não existe");
        }
    }
}
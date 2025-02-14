package com.br.TravelEasy.Auth.Service;


import com.br.TravelEasy.Auth.Feing.Email.MessagePublisher;
import com.br.TravelEasy.Auth.Feing.Email.EmailSmsRequest;
import com.br.TravelEasy.Auth.Feing.User.User;
import com.br.TravelEasy.Auth.Feing.User.UserRespository;
import com.br.TravelEasy.Auth.Feing.User.UserVerification;
import com.br.TravelEasy.Auth.Feing.User.UserVerificationRepository;
import com.br.TravelEasy.Auth.Feing.request.DTOUserRegister;
import com.br.TravelEasy.Auth.Feing.response.DTOUserDetails;
import com.br.TravelEasy.Auth.Utils.EncryptDecrypt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    @Autowired
    private UserRespository userRepository;
    @Autowired
    private UserVerificationRepository userVerificationRepository;
    @Autowired
    private EncryptDecrypt encryptDecrypt;

    @Autowired
    MessagePublisher messagePublisher;

    public DTOUserDetails createUser(@Valid DTOUserRegister userData) throws Exception {
        var user = new User(userData);
        var userExists = userRepository.getReferenceByCpf(user.getCpf());

        if (userExists == null) {
            user.setPassword(encryptDecrypt.passwordEncoder().encode(user.getPassword()));
            UserVerification userVerification = new UserVerification(user);
            var secret = EncryptDecrypt.getSecretKey();




            EmailSmsRequest emailSmsRequest = new EmailSmsRequest(user.getEmail(), userVerification.getVerificationCode(),user.getDdd(),user.getTel(),userData.verificationType());
            messagePublisher.publishEmail(emailSmsRequest);

//               TODO USAR NO SERVIÇO DE EMAIL
//            if (userData.verificationType() == Verificationtype.EMAIL) {
//                //chamar serviço de envio de mensagem (email/Sms)
//
//
//                String mensagemHtml = htmlTemplateService.getTemplateContent(1L);
//
//                EmailMessage emailMessage = getEmailMessage(user, mensagemHtm);
//
//                emailPublisher.publishEmail(emailMessage);
//
//            } else if (userData.verificationType() == Verificationtype.SMS) {
//                String destinationNumber = "+55" + user.getDdd() + user.getTel();
//                smsService.sendSMS(destinationNumber, "Seu codigo de verificação é: " + userVerification.getVerificationCode());
//            }

//            TODO CHAMAR CLIENTE NO SERVIÇO TRANSACIONAL

            userRepository.save(user);
            userVerification.setVerificationCode(EncryptDecrypt.encrypt(userVerification.getVerificationCode(), secret));
            userVerificationRepository.save(userVerification);
            return new DTOUserDetails(user, userVerification);
        } else {
            throw new Exception("Usuario ja existente");
        }
    }


}

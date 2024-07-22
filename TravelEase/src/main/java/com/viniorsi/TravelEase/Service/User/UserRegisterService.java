package com.viniorsi.TravelEase.Service.User;

import com.viniorsi.TravelEase.Domain.Email.EmailMessage;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserDetails;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserRegister;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Enums.StatusEnum;
import com.viniorsi.TravelEase.Domain.User.Enums.Verificationtype;
import com.viniorsi.TravelEase.Domain.UserVerification.Entity.UserVerification;
import com.viniorsi.TravelEase.Repository.User.UserRespository;
import com.viniorsi.TravelEase.Repository.UserVerification.UserVerificationRepository;
import com.viniorsi.TravelEase.Service.Email.EmailPublisher;
import com.viniorsi.TravelEase.Service.SMS.SMSService;
import com.viniorsi.TravelEase.Service.html.HtmlTemplateService;
import com.viniorsi.TravelEase.Utils.EncryptDecrypt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserRegisterService {

    @Autowired
    private UserRespository userRepository;
    @Autowired
    private UserVerificationRepository userVerificationRepository;
    @Autowired
    private EncryptDecrypt encryptDecrypt;
    @Autowired
    private HtmlTemplateService htmlTemplateService;
    @Autowired
    private EmailPublisher emailPublisher;
    @Autowired
    private SMSService smsService;

    public DTOUserDetails createUser(@Valid DTOUserRegister userData) throws Exception {
        var user = new User(userData);
        var userExists = userRepository.getReferenceByCpf(user.getCpf());

        if (userExists == null) {
            user.setPassword(encryptDecrypt.passwordEncoder().encode(user.getPassword()));
            UserVerification userVerification = new UserVerification(user);
            var secret = EncryptDecrypt.getSecretKey();

            if(userData.verificationType() == Verificationtype.EMAIL){
                String mensagemHtml = htmlTemplateService.getTemplateContent(1L);

                EmailMessage emailMessage = getEmailMessage(user, mensagemHtml, userVerification);

                emailPublisher.publishEmail(emailMessage);

            } else if (userData.verificationType() == Verificationtype.SMS){
               String destinationNumber = "+55" + user.getDdd() + user.getTel();
                smsService.sendSMS(destinationNumber, "Seu codigo de verificação é: " + userVerification.getVerificationCode());
            }

            userRepository.save(user);
            userVerification.setVerificationCode(EncryptDecrypt.encrypt(userVerification.getVerificationCode(), secret));
            userVerificationRepository.save(userVerification);
            return new DTOUserDetails(user, userVerification);
        } else {
            throw new Exception("Usuario ja existente");
        }
    }

    private static EmailMessage getEmailMessage(User user, String mensagemHtml, UserVerification userVerification) {
        List<String> caminhosImagems = Arrays.asList(
                "src/Assets/email/TravelEase.png",
                "src/Assets/email/ImagemTravelEaseEmail.jpg"
        );
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(user.getEmail());
        emailMessage.setSubject("Welcome to TravelEase!");
        emailMessage.setBody(mensagemHtml.replace("1 2 3 4 5 6", userVerification.getVerificationCode()));
        emailMessage.setAttachments(caminhosImagems);
        return emailMessage;
    }
}

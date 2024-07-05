package com.viniorsi.TravelEase.Service.User;

import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserDetails;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserVerificationStatus;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Enums.StatusEnum;
import com.viniorsi.TravelEase.Domain.UserVerification.UserVerification;
import com.viniorsi.TravelEase.Repository.User.UserRespository;
import com.viniorsi.TravelEase.Repository.UserVerification.UserVerificationRepository;
import com.viniorsi.TravelEase.Service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserVerificationRepository userVerificationRepository;



    public DTOUserDetails createUser(User userData) {

           userData.setPassword(passwordEncoder().encode(userData.getPassword()));
           userRepository.save(userData);
           UserVerification userVerification = new UserVerification(userData);
           userVerificationRepository.save(userVerification);
        var email = emailService.enviarEmailTexto(userData.getEmail(),
                "Welcome to TravelEase!",
                "Hello, " + userData.getName() + "! This is your code to finish your registration: " + userVerification.getUuid() );
           return new DTOUserDetails(userData,userVerification);
    }


    public User statusVerification(String cpf, String uuid){
        User user = userRepository.findByCpf(cpf);
        UserVerification userVerification = userVerificationRepository.findByUser(user);
        if(userVerification.getUuid().equals(uuid)){
            user.setStatus(StatusEnum.A);
            userRepository.save(user);
            return user;
        }
        return null;

    }


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}

package com.viniorsi.TravelEase.Domain.User.Service;

import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserDetails;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserRegister;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private EmailService emailService;

    public DTOUserDetails createUser(User userData) {

       var email = emailService.enviarEmailTexto(userData.getEmail(),
                "Novo usuário cadastrado",
                "Você está recebendo um email de cadastro o número para validação é ");
       if(email != null){
        userRepository.save(userData);
        return new DTOUserDetails(userData);
       }
         return null;
    }



}

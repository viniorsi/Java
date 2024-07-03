package com.viniorsi.TravelEase.Domain.User.Service;

import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserDetails;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserRegister;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Enum.Status;
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
                "Welcome to TravelEase!",
                "Hello, " + userData.getName() + "! This is your code to finish your registration: " + numeroVerificador());

       if(email != null){
        userRepository.save(userData);
        return new DTOUserDetails(userData);
       }
         return null;
    }

    public int numeroVerificador(){
        int min = 10000;
        int max = 99999;
        return  min + (int)(Math.random() * ((max - min) + 1));

    }

    public Long verificarCadastro(Long id){
        var user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get().getId();
        }
        return null;
    }


    public void atualizarStatus(String email) {
        User user = (User) userRepository.findByEmail(email);
        if(user != null){
            user.setStatus(Status.ATIVO);
            userRepository.save(user);
        }


    }
}

package com.viniorsi.TravelEase.Controller;

import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserLogin;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserRegister;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.UserVerification.DTO.DTOUserVerificationStatus;
import com.viniorsi.TravelEase.Domain.UserVerification.DTO.DTOUserVerificationStatusRequest;
import com.viniorsi.TravelEase.Service.Auth.LoginService;
import com.viniorsi.TravelEase.Service.User.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    LoginService loginService;



    @PostMapping("/register")
    @Transactional

    public ResponseEntity createUser(@RequestBody @Valid DTOUserRegister dtoUserRegister, UriComponentsBuilder uriBuilder) {
        //to do diminuir o tempo de resposta colocando hmtl em arquivo separado ou no bd e as imagens tambem
        try {
            var user = new User(dtoUserRegister);
            var uri = uriBuilder.path("/login/{id}").buildAndExpand(user.getId()).toUri();
            var userDetails = userService.createUser(user);
            return ResponseEntity.created(uri).body(userDetails);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/singin")
    public ResponseEntity singIn(@RequestBody @Valid DTOUserLogin dtoUsuarioLogin) {
        try {
            var token = loginService.Login(dtoUsuarioLogin);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/codeVerification")
    @Transactional
    public ResponseEntity codeVerifications(@RequestBody @Valid DTOUserVerificationStatusRequest dtoUserVerificationStatusRequest) {
        try {
            var user = userService.codeVerification(dtoUserVerificationStatusRequest.cpf(), dtoUserVerificationStatusRequest.verificationCode());
            if (user != null) {
                return ResponseEntity.ok(new DTOUserVerificationStatus(user));
            }
            return ResponseEntity.badRequest().body("Invalid code");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}

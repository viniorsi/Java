package com.viniorsi.TravelEase.Controller;

import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserRegister;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.UserVerification.DTO.DTOUserVerificationStatus;
import com.viniorsi.TravelEase.Domain.UserVerification.DTO.DTOUserVerificationStatusRequest;
import com.viniorsi.TravelEase.Service.User.UserRegisterService;
import com.viniorsi.TravelEase.Service.User.UserVerificationCodeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private UserVerificationCodeService userVerificationCodeService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid DTOUserRegister dtoUserRegister, UriComponentsBuilder uriBuilder) {
        try {
            var user = new User(dtoUserRegister);
            var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
            var userDetails = userRegisterService.createUser(dtoUserRegister);
            return ResponseEntity.created(uri).body(userDetails);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/codeVerification")
    @Transactional
    public ResponseEntity codeVerifications(@RequestBody @Valid DTOUserVerificationStatusRequest dtoUserVerificationStatusRequest) {
        try {
            var user = userVerificationCodeService.codeVerification(dtoUserVerificationStatusRequest.cpf(), dtoUserVerificationStatusRequest.verificationCode());
            if (user != null) {
                return ResponseEntity.ok(new DTOUserVerificationStatus(user));
            }
            return ResponseEntity.badRequest().body("Invalid code");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

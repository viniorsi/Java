package com.viniorsi.TravelEase.Controller;

import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserLogin;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserRegister;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserVerificationStatus;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserVerificationStatusRequest;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Service.User.UserService;
import com.viniorsi.TravelEase.Infra.Security.DTOTokenJWT;
import com.viniorsi.TravelEase.Infra.Security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid DTOUserRegister dtoUserRegister, UriComponentsBuilder uriBuilder) {

        var user = new User(dtoUserRegister);
        var uri = uriBuilder.path("/login/{id}").buildAndExpand(user.getId()).toUri();
        var userDetails = userService.createUser(user);

        return ResponseEntity.created(uri).body(userDetails);

    }

    @PostMapping("/singin")
    public ResponseEntity singIn(@RequestBody @Valid DTOUserLogin dtoUsuarioLogin) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dtoUsuarioLogin.login(), dtoUsuarioLogin.senha());
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new DTOTokenJWT(tokenJWT));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/status")
    public ResponseEntity verificationStatus(@RequestBody @Valid DTOUserVerificationStatusRequest dtoUserVerificationStatusRequest) {
        var user = userService.statusVerification(dtoUserVerificationStatusRequest.cpf(), dtoUserVerificationStatusRequest.uuid());
        if (user != null) {
            return ResponseEntity.ok(new DTOUserVerificationStatus(user));
        }
        return ResponseEntity.badRequest().body("Invalid code");
    }


}

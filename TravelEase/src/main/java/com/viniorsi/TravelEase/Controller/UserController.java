package com.viniorsi.TravelEase.Controller;

import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserLogin;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserRegister;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Service.UserService;
import com.viniorsi.TravelEase.Domain.User.Service.VerificationService;
import com.viniorsi.TravelEase.Infra.Security.DTOTokenJWT;
import com.viniorsi.TravelEase.Infra.Security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
        try{
        var authenticationToken = new UsernamePasswordAuthenticationToken(dtoUsuarioLogin.login(), dtoUsuarioLogin.senha());
       var authentication = manager.authenticate(authenticationToken);
       var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DTOTokenJWT(tokenJWT));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/verificarEmail")
//    public String verificarCadastro(@RequestParam Long id){
//        var user = userService.verificarCadastro(id);
//        if(user != null){
//            return "Usuário cadastrado";
//        }
//        return "Usuário não cadastrado";
//    }

    @PostMapping("/sendVerificationCode")
    public String sendVerificationCode(@RequestParam String email) {
        return VerificationService.generateAndSendVerificationCode(email);
    }

    @PostMapping("/verifyCode")
    public ResponseEntity verifyCode(@RequestParam String email, @RequestParam String code) {
        if(VerificationService.verifyCode(email, code)){
            userService.atualizarStatus(email);
            return ResponseEntity.ok().body("Código verificado com sucesso!");
        }
        return ResponseEntity.status(401).body("Código inválido!");
    }

}

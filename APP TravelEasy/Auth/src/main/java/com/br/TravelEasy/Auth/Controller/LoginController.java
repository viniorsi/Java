package com.br.TravelEasy.Auth.Controller;

import com.br.TravelEasy.Auth.Feing.request.DTOUserLogin;
import com.br.TravelEasy.Auth.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping()
    public ResponseEntity singIn(@RequestBody @Valid DTOUserLogin dtoUsuarioLogin) {
        try {
            var token = loginService.Login(dtoUsuarioLogin);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

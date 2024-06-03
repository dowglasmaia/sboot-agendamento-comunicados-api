package com.dowglasmaia.agendamento.controller;


import com.dowglasmaia.agendamento.models.User;
import com.dowglasmaia.agendamento.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user){
        return loginService.login(user);
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam("refresh_token") String refreshToken){
        return loginService.refreshToken(refreshToken);
    }
}

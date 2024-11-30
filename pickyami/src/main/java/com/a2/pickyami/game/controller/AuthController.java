package com.a2.pickyami.game.controller;

import com.a2.pickyami.game.model.LoginRequest;
import com.a2.pickyami.game.model.RegisterRequest;
import com.a2.pickyami.game.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    final private AuthService service;

    @PostMapping(value = "/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        return service.verify(loginRequest);
    }

    @PostMapping(value = "/register")
    public String register(@Valid @RequestBody RegisterRequest register) {
        return service.register(register);
    }

}

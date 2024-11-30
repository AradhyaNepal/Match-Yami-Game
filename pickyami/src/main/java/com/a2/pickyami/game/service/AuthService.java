
package com.a2.pickyami.game.service;

import com.a2.pickyami.game.config.JwtService;
import com.a2.pickyami.game.entity.Players;
import com.a2.pickyami.game.model.LoginRequest;
import com.a2.pickyami.game.model.RegisterRequest;
import com.a2.pickyami.game.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    final private AuthenticationManager authManager;
    final private JwtService jwtService;
    final private PlayerRepository playerRepository;


    public String verify(LoginRequest request) {
        var authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            var user = playerRepository.findByEmail(request.getEmail());
            return jwtService.generateToken(user.get());
        } else {
            return "fail";
        }
    }

    public String register(RegisterRequest request) {
        var user = playerRepository.save(
                Players.builder().email(
                                request.getEmail())
                        .fullName(request.getFullName())
                        .password(request.getPassword())
                        .build());
        return jwtService.generateToken(user);
    }


}

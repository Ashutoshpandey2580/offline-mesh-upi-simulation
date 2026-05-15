package com.example.upimesh.service;

import com.example .upimesh.config.JwtService;
import com.example.upimesh.dto.*;
import com.example.upimesh.entity.Role;
import com.example.upimesh.entity.User;
import com.example.upimesh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthResponse signup(SignupRequest request) {

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setRole(Role.USER);

        userRepository.save(user);

        String token =
                jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        User user =
                userRepository.findByEmail(
                                request.getEmail())
                        .orElseThrow();

        boolean match =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!match) {
            throw new RuntimeException(
                    "Invalid credentials");
        }

        String token =
                jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}
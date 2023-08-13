package me.hashemalayan.authservice.controllers;

import me.hashemalayan.authservice.domain.User;
import me.hashemalayan.authservice.dtos.AuthDto;
import me.hashemalayan.authservice.repositories.AuthRepository;
import me.hashemalayan.authservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signUp")
public class SignUpController {

    final AuthService authService;

    @Autowired
    public SignUpController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<User> signup(@RequestBody AuthDto dto) {

        return new ResponseEntity<>(
                authService.createUser(dto.email(), dto.password()),
                HttpStatus.CREATED
        );
    }
}

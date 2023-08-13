package me.hashemalayan.authservice.controllers;

import me.hashemalayan.authservice.dtos.AuthDto;
import me.hashemalayan.authservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signIn")
public class SignInController {

    final AuthService authService;
    @Autowired
    public SignInController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> signIn(@RequestBody AuthDto dto) {

        var user = authService.authenticate(dto.email(), dto.password());

        if(user != null)
            return ResponseEntity.ok(user);

        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }
}

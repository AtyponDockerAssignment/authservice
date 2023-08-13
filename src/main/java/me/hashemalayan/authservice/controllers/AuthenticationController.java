package me.hashemalayan.authservice.controllers;

import me.hashemalayan.authservice.dtos.AuthenticationRequest;
import me.hashemalayan.authservice.dtos.RegistrationRequest;
import me.hashemalayan.authservice.dtos.AuthenticationResponse;
import me.hashemalayan.authservice.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    final AuthenticationService authService;

    @Autowired
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) {
      return ResponseEntity.ok(
              authService.register(request)
      );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(
                authService.authenticate(request)
        );
    }
}

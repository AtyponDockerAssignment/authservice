package me.hashemalayan.authservice.services;

import me.hashemalayan.authservice.domain.User;
import me.hashemalayan.authservice.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    final AuthRepository authRepository;
    final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(
            AuthRepository authRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String email, String password) {
        var user = authRepository.findByEmail(email).orElse(null);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User createUser(String email, String password) {

        var user = new User(0L, email, passwordEncoder.encode(password));
        return authRepository.save(user);
    }
}

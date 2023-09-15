package pl.gornicki.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gornicki.userservice.security.AuthRequest;
import pl.gornicki.userservice.security.AuthResponse;
import pl.gornicki.userservice.security.RegisterRequest;
import pl.gornicki.userservice.security.RegisterResponse;

@Service
@RequiredArgsConstructor
public class AuthService {
    public RegisterResponse register(RegisterRequest request) {
        throw new RuntimeException("NOT IMPLEMENTED YET");
    }

    public AuthResponse authorize(AuthRequest request) {
        throw new RuntimeException("NOT IMPLEMENTED YET");
    }

    public AuthResponse refreshAccessToken(String authHeader) {
        throw new RuntimeException("NOT IMPLEMENTED YET");
    }
}

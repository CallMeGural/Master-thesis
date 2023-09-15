package pl.gornicki.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gornicki.userservice.security.AuthRequest;
import pl.gornicki.userservice.security.AuthResponse;
import pl.gornicki.userservice.security.RegisterRequest;
import pl.gornicki.userservice.security.RegisterResponse;
import pl.gornicki.userservice.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authorize")
    public ResponseEntity<AuthResponse> authorizeUser(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authorize(request));
    }

}

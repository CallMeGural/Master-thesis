package pl.gornicki.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gornicki.userservice.repository.UserRepository;
import pl.gornicki.userservice.model.User;
import pl.gornicki.userservice.security.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final SecurityConfig config;

    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.findByEmail(request.email()).isPresent()) throw new RuntimeException("Email already taken");
        User user = User.builder()
                .email(request.email())
                .password(config.encoder().encode(request.password()))
                .username(request.username())
                .build();
        userRepository.save(user);
        return new RegisterResponse("User "+request.username()+" registered successfully!");
    }

    public AuthResponse authorize(AuthRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(config.encoder().matches(request.password(),user.getPassword()))
            return new AuthResponse(user.getId());
        throw new RuntimeException("Wrong credentials");
    }
}

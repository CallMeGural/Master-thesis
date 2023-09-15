package pl.gornicki.userservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gornicki.userservice.UserRepository;
import pl.gornicki.userservice.dto.UserResponseDto;
import pl.gornicki.userservice.model.User;
import pl.gornicki.userservice.security.SecurityConfig;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User updateUser(User user) {
        User userToUpdate = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        userToUpdate.setUsername(user.getUsername());
        return userRepository.save(userToUpdate);
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

package pl.gornicki.userservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gornicki.userservice.config.Config;
import pl.gornicki.userservice.repository.UserRepository;
import pl.gornicki.userservice.model.User;

import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Config config;
    private final String URL = "http://post-service:8080";

    @Transactional
    public User updateUser(User user) {
        User userToUpdate = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        userToUpdate.setUsername(user.getUsername());
        return userRepository.save(userToUpdate);
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
        deleteUserPostAndComments(userId);
    }

    private void deleteUserPostAndComments(UUID userId) {
        config.restTemplate().delete(URI.create(URL + "/posts/users/" + userId));
//        config.restTemplate().delete(URI.create(URL + "/comments/users/" + userId));
    }
}

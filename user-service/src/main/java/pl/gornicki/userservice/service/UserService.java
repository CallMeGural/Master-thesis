package pl.gornicki.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gornicki.userservice.model.User;

@Service
@RequiredArgsConstructor
public class UserService {
    public User getMe(String authHeader) {
        throw new RuntimeException("Not implemented yet");
    }

    public User updateUser(User user) {
        throw new RuntimeException("Not implemented yet");
    }
}

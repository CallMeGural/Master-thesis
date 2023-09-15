package pl.gornicki.userservice.security;

public record AuthRequest(String email, String password) {
}

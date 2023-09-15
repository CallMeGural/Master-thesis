package pl.gornicki.userservice.security;

public record AuthResponse(String id, String accessToken, String refreshToken) {
}

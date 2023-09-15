package pl.gornicki.userservice.security;

public record RegisterRequest(String username, String email, String password) {}

package com.example.cinema.auth;

public record AuthenticationRequest(
        String email,
        String password
) {}

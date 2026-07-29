package com.example.cinema.auth;

public record RegisterRequest(
        String email,
        String password
) {}
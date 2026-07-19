package com.example.cinema.hall;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record HallDto(
        Long id,
        @NotBlank
        String name,
        @Positive
        Integer capacity
) {
}

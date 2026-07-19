package com.example.cinema.session;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record SessionDto(
        Long id,
        @NotNull
        LocalDateTime startTime,
        @Positive
        Integer ticketPrice,
        @NotNull
        @Positive
        Long movieId,
        @NotNull
        @Positive
        Long hallId
) {
}

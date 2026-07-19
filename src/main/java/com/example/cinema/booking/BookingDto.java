package com.example.cinema.booking;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookingDto(
        Long id,
        @NotNull
        @Positive
        Integer seatNumber,
        @NotNull
        @Positive
        Long sessionId,
        @Positive
        @NotNull
        Long userId
){
}

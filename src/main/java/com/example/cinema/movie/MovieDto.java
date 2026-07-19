package com.example.cinema.movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MovieDto (
        Long id,
        @NotBlank
        String title,
        @NotBlank
        @Size(max = 1000)
        String description,
        @Positive
        Integer duration,
        @NotNull
        AgeRating ageRating,
        @NotNull
        Genre genre
){
}

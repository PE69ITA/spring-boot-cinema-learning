package com.example.cinema.movie;

import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieDto toDto(MovieEntity entity){
        return new MovieDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDuration(),
                entity.getAgeRating(),
                entity.getGenre()
        );
    }
    public MovieEntity toEntity(MovieDto dto){
        return new MovieEntity(
                dto.id(),
                dto.title(),
                dto.description(),
                dto.duration(),
                dto.ageRating(),
                dto.genre()
        );
    }
}

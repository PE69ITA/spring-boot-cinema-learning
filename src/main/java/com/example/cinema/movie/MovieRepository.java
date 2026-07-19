package com.example.cinema.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    List<MovieEntity> findByGenre(Genre genre);
    List<MovieEntity> findByAgeRating(AgeRating ageRating);
    List<MovieEntity> findByTitleContainingIgnoreCase(String title);
}

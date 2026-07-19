package com.example.cinema.movie;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Page<MovieDto>> getAllMovies(Pageable pageable){
        return ResponseEntity.ok()
                .body(movieService.getAllMovies(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(movieService.getMovieById(id));
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody @Valid MovieDto movieToCreate){
        return ResponseEntity.status(201)
                .body(movieService.createMovie(movieToCreate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody @Valid MovieDto movieToUpdate){
        return ResponseEntity.ok()
                .body(movieService.updateMovie(id, movieToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/genre")
    public ResponseEntity<List<MovieDto>> getMoviesByGenre(@RequestParam Genre genre){
        return ResponseEntity.ok()
                .body(movieService.getMoviesByGenre(genre));
    }

    @GetMapping("/age")
    public ResponseEntity<List<MovieDto>> getMoviesByAgeRating(@RequestParam AgeRating ageRating){
        return ResponseEntity.ok()
                .body(movieService.getMoviesByAgeRating(ageRating));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> searchMoviesByTitle(@RequestParam String title){
        return ResponseEntity.ok(movieService.searchMovies(title));
    }
}

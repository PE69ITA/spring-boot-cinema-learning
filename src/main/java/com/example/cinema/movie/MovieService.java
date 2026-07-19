package com.example.cinema.movie;

import com.example.cinema.exception.MovieNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public Page<MovieDto> getAllMovies(Pageable pageable){
       return movieRepository.findAll(pageable)
               .map(movieMapper::toDto);
    }

    public MovieDto getMovieById(Long id){
        return movieRepository.findById(id)
                .map(movieMapper::toDto)
                .orElseThrow(()->new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    public MovieDto createMovie(MovieDto dto){
        MovieEntity entityToSave =movieMapper.toEntity(dto);
        MovieEntity savedEntity  = movieRepository.save(entityToSave);
        return movieMapper.toDto(savedEntity);
    }

    public MovieDto updateMovie(Long id, MovieDto dto){
        var entity =movieRepository.findById(id)
                .orElseThrow(()->new MovieNotFoundException("Movie with id " + id + " not found"));
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setDuration(dto.duration());
        entity.setAgeRating(dto.ageRating());
        entity.setGenre(dto.genre());

        var savedEntity = movieRepository.save(entity);
        return movieMapper.toDto(savedEntity);
    }

    public void deleteMovie(Long id){
        movieRepository.findById(id)
                .orElseThrow(()->new MovieNotFoundException("Movie with id " + id + " not found"));
        movieRepository.deleteById(id);
    }

    public List<MovieDto> getMoviesByGenre(Genre genre){
        return movieRepository.findByGenre(genre)
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }

    public List<MovieDto> getMoviesByAgeRating(AgeRating ageRating){
        return movieRepository.findByAgeRating(ageRating)
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }

    public List<MovieDto> searchMovies(String title){
        return movieRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }
}

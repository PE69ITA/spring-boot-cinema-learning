package com.example.cinema.session;


import com.example.cinema.exception.MovieNotFoundException;
import com.example.cinema.exception.SessionNotFoundException;
import com.example.cinema.hall.HallEntity;
import com.example.cinema.hall.HallRepository;
import com.example.cinema.movie.MovieEntity;
import com.example.cinema.movie.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;

    public SessionService(SessionRepository sessionRepository, SessionMapper sessionMapper, MovieRepository movieRepository, HallRepository hallRepository) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.movieRepository = movieRepository;
        this.hallRepository = hallRepository;
    }
    public List<SessionDto> getAllSessions(){
        return sessionRepository.findAll()
                .stream().map(sessionMapper::toDto)
                .toList();
    }

    public SessionDto getSessionById(Long id){
        return sessionRepository.findById(id)
                .map(sessionMapper::toDto)
                .orElseThrow(()->new SessionNotFoundException("Session not found"));
    }

    public SessionDto createSession(SessionDto dto){
        MovieEntity movie = movieRepository.findById(dto.movieId())
                .orElseThrow(()->new MovieNotFoundException("Movie not found"));
        HallEntity hall = hallRepository.findById(dto.hallId())
                .orElseThrow(()->new IllegalArgumentException("Hall not found"));
        SessionEntity entity =new SessionEntity();
        entity.setStartTime(dto.startTime());
        entity.setTicketPrice(dto.ticketPrice());
        entity.setMovie(movie);
        entity.setHall(hall);
        SessionEntity savedEntity =sessionRepository.save(entity);
        return sessionMapper.toDto(savedEntity);
    }

    public SessionDto updateSession(Long id, SessionDto dto){
        var entity = sessionRepository.findById(id)
                .orElseThrow(()->new SessionNotFoundException("Session not found"));
        MovieEntity movie = movieRepository.findById(dto.movieId())
                .orElseThrow(()->new MovieNotFoundException("Movie not found"));
        HallEntity hall = hallRepository.findById(dto.hallId())
                .orElseThrow(()->new IllegalArgumentException("Hall not found"));

        entity.setStartTime(dto.startTime());
        entity.setTicketPrice(dto.ticketPrice());
        entity.setMovie(movie);
        entity.setHall(hall);
        SessionEntity savedEntity = sessionRepository.save(entity);
        return sessionMapper.toDto(savedEntity);
    }


    public void deleteSession(Long id){
        sessionRepository.findById(id)
                .orElseThrow(()->new SessionNotFoundException("Session not found"));
        sessionRepository.deleteById(id);
    }

    public List<SessionDto> getSessionsByMovieId(Long movieId){
        return sessionRepository.findByMovieId(movieId)
                .stream()
                .map(sessionMapper::toDto)
                .toList();
    }
    public List<SessionDto> getSessionsByHallId(Long hallId){
        return sessionRepository.findByHallId(hallId)
                .stream()
                .map(sessionMapper::toDto)
                .toList();
    }
}

package com.example.cinema.session;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    List<SessionEntity> findByMovieId(Long movieId);
    List<SessionEntity> findByHallId(Long hallId);
}

package com.example.cinema.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    boolean existsBySessionIdAndSeatNumber(Long sessionId, Integer seatNumber);
    List<BookingEntity> findByUserId(Long userId);
    List<BookingEntity> findBySessionId(Long sessionId);
}

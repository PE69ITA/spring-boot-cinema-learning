package com.example.cinema.user;

import com.example.cinema.booking.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

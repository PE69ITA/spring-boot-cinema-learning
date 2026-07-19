package com.example.cinema.booking;

import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public BookingDto toDto(BookingEntity entity){
        return new BookingDto(
                entity.getId(),
                entity.getSeatNumber(),
                entity.getSession().getId(),
                entity.getUser().getId()
        );
    }
}

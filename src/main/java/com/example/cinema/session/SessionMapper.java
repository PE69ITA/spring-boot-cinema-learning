package com.example.cinema.session;

import org.springframework.stereotype.Component;

@Component
public class SessionMapper {
    public SessionDto toDto(SessionEntity entity){
        return new SessionDto(
                entity.getId(),
                entity.getStartTime(),
                entity.getTicketPrice(),
                entity.getMovie().getId(),
                entity.getHall().getId()
        );
    }
}

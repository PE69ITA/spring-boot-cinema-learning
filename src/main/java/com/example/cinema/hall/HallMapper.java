package com.example.cinema.hall;

import org.springframework.stereotype.Component;

@Component
public class HallMapper {
    public HallDto toDto(HallEntity entity){
        return new HallDto(
                entity.getId(),
                entity.getName(),
                entity.getCapacity()
        );
    }
    public HallEntity toEntity(HallDto dto){
        return new HallEntity(
                dto.id(),
                dto.name(),
                dto.capacity()
        );
    }
}

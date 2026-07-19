package com.example.cinema.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(UserEntity entity){
        return new UserDto(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword()
        );
    }
    public UserEntity toEntity(UserDto dto){
        return new UserEntity(
                dto.id(),
                dto.email(),
                dto.password()
        );
    }
}

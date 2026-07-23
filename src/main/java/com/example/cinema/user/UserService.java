package com.example.cinema.user;

import com.example.cinema.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getUserById(Long id){
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(()->new UserNotFoundException("User not found"));
    }

    public UserDto createUser(UserDto dto){
        UserEntity entity = userMapper.toEntity(dto);
        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toDto(savedEntity);
    }

    public UserDto updatePassword(Long id, UpdatePasswordRequest request){
        var entity = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found"));
        entity.setPassword(request.password());
        var savedEntity = userRepository.save(entity);
        return userMapper.toDto(savedEntity);
    }

    public void deleteUser(Long id){
        userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found"));
        userRepository.deleteById(id);
    }
}

package com.example.cinema.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok()
                .body(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(userService.getUserById(id));
    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userToCreate){
        return ResponseEntity.status(201)
                .body(userService.createUser(userToCreate));
    }
    @PatchMapping("/{id}/password")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UpdatePasswordRequest request){
        return ResponseEntity.ok()
                .body(userService.updatePassword(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

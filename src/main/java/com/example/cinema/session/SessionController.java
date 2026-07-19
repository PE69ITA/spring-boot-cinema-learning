package com.example.cinema.session;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public ResponseEntity<List<SessionDto>> getAllSessions(){
        return ResponseEntity.ok()
                .body(sessionService.getAllSessions());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> getSessionById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(sessionService.getSessionById(id));
    }
    @PostMapping
    public ResponseEntity<SessionDto> createSession(@RequestBody @Valid SessionDto session){
        return ResponseEntity.status(201)
                .body(sessionService.createSession(session));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SessionDto> updateSession(@PathVariable Long id, @RequestBody @Valid SessionDto session){
        return ResponseEntity.ok()
                .body(sessionService.updateSession(id, session));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id){
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movie")
    public ResponseEntity<List<SessionDto>> getSessionsByMovieId(@RequestParam Long movieId){
        return ResponseEntity.ok()
                .body(sessionService.getSessionsByMovieId(movieId));
    }

    @GetMapping("/hall")
    public ResponseEntity<List<SessionDto>> getSessionsByHallId(@RequestParam Long hallId){
        return ResponseEntity.ok(
                sessionService.getSessionsByHallId(hallId)
        );
    }
}

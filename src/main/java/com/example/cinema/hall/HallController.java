package com.example.cinema.hall;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/halls")
public class HallController {
    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping
    public ResponseEntity<List<HallDto>> getAllHalls(){
        return ResponseEntity.ok()
                .body(hallService.getAllHalls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallDto> getHallById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(hallService.getHallById(id));
    }

    @PostMapping
    public ResponseEntity<HallDto> createHall(@RequestBody @Valid HallDto hallToCreate){
        return ResponseEntity.status(201)
                .body(hallService.createHall(hallToCreate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HallDto> updateHall(@PathVariable Long id, @RequestBody @Valid HallDto hallToUpdate){
        return ResponseEntity.ok()
                .body(hallService.updateHall(id, hallToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id){
        hallService.deleteHall(id);
        return ResponseEntity.noContent().build();
    }
}

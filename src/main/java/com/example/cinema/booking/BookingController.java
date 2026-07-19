package com.example.cinema.booking;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings(){
        return ResponseEntity.ok()
                .body(bookingService.getAllBookings());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(bookingService.getBookingById(id));
    }
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody @Valid BookingDto create){
        return ResponseEntity.status(201)
                .body(bookingService.createBooking(create));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long id, @RequestBody @Valid BookingDto update){
        return ResponseEntity.ok()
                .body(bookingService.updateBooking(id, update));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<BookingDto>> getBookingsByUserId(@RequestParam Long userId){
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }
    @GetMapping("/session")
    public ResponseEntity<List<BookingDto>> getBookingsBySessionId(@RequestParam Long sessionId){
        return ResponseEntity.ok(bookingService.getBookingsBySessionId(sessionId));
    }
}

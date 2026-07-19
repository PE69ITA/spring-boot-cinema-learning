package com.example.cinema.booking;

import com.example.cinema.exception.*;
import com.example.cinema.session.SessionEntity;
import com.example.cinema.session.SessionRepository;
import com.example.cinema.user.UserEntity;
import com.example.cinema.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, SessionRepository sessionRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bookingMapper = bookingMapper;
    }

    public List<BookingDto> getAllBookings(){
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    public BookingDto getBookingById(Long id){
        return bookingRepository.findById(id)
                .map(bookingMapper::toDto)
                .orElseThrow(()->new BookingNotFoundException("Booking not found"));
    }

    public BookingDto createBooking(BookingDto dto){

        UserEntity user = userRepository.findById(dto.userId())
                .orElseThrow(()->new UserNotFoundException("User not found"));
        SessionEntity session = sessionRepository.findById(dto.sessionId())
                .orElseThrow(()->new SessionNotFoundException("Session not found"));
        if (dto.seatNumber()>session.getHall().getCapacity()){
            throw new InvalidSeatNumberException("Seat number exceeds hall capacity");
        }
        if (bookingRepository.existsBySessionIdAndSeatNumber(
                dto.sessionId(),
                dto.seatNumber()
        )){
            throw new SeatAlreadyBookedException("Seat is already booked");
        }

        BookingEntity entity = new BookingEntity();
        entity.setSeatNumber(dto.seatNumber());
        entity.setSession(session);
        entity.setUser(user);
        var savedEntity = bookingRepository.save(entity);
        return bookingMapper.toDto(savedEntity);
    }

    public BookingDto updateBooking(Long id, BookingDto dto){
        var entity = bookingRepository.findById(id)
                .orElseThrow(()->new BookingNotFoundException("Booking not found"));
        UserEntity user = userRepository.findById(dto.userId())
                .orElseThrow(()->new UserNotFoundException("User not found"));
        SessionEntity session = sessionRepository.findById(dto.sessionId())
                .orElseThrow(()->new SessionNotFoundException("Session not found"));
        if (dto.seatNumber()>session.getHall().getCapacity()){
            throw new InvalidSeatNumberException("Seat number exceeds hall capacity");
        }
        if ((!entity.getSeatNumber().equals(dto.seatNumber())
                || !entity.getSession().getId().equals(dto.sessionId()))
                && bookingRepository.existsBySessionIdAndSeatNumber(
                dto.sessionId(),
                dto.seatNumber()
        )) {
            throw new SeatAlreadyBookedException("Seat is already booked");
        }
        entity.setSeatNumber(dto.seatNumber());
        entity.setSession(session);
        entity.setUser(user);
        BookingEntity savedEntity=bookingRepository.save(entity);
        return bookingMapper.toDto(savedEntity);
    }

    public void deleteBooking(Long id){
        bookingRepository.findById(id)
                .orElseThrow(()->new BookingNotFoundException("Booking not found"));
        bookingRepository.deleteById(id);
    }

    public List<BookingDto> getBookingsByUserId(Long userId){
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }
    public List<BookingDto> getBookingsBySessionId(Long sessionId){
        return bookingRepository.findBySessionId(sessionId)
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }
}

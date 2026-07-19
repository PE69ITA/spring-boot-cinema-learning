package com.example.cinema.booking;

import com.example.cinema.session.SessionEntity;
import com.example.cinema.user.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer seatNumber;
    @ManyToOne
    @JoinColumn(name = "session_id")
    private SessionEntity session;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public BookingEntity(){}

    public BookingEntity(Long id, Integer seatNumber, SessionEntity session, UserEntity user) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.session = session;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

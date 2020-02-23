package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.entity.Reservation;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository {
    List<Reservation> getReservations();
    boolean makeReservation(Reservation reservation);
}

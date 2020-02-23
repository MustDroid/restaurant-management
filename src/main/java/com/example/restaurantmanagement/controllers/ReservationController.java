package com.example.restaurantmanagement.controllers;

import com.example.restaurantmanagement.connections.MySQLDatabaseConnection;
import com.example.restaurantmanagement.entity.Reservation;
import com.example.restaurantmanagement.repository.DatabaseReservationRepository;
import com.example.restaurantmanagement.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class ReservationController {
    private ReservationRepository reservationRepository = new DatabaseReservationRepository(MySQLDatabaseConnection.getInstance().getConnection());

    @CrossOrigin
    @GetMapping("/reservations")
    public ResponseEntity<List> getReservations() {
        List<Reservation> reservations = reservationRepository.getReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/reservations")
    public ResponseEntity<List> makeReservation(@RequestBody Reservation reservation) {
        if(reservationRepository.makeReservation(reservation)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}

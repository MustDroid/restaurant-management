package com.example.restaurantmanagement.entity;

import java.sql.Timestamp;

public class Reservation {
    private Integer id;
    private Integer idCustomer;
    private Integer idTable;
    private Integer numberOfSeats;
    private Timestamp reservationFrom;
    private Timestamp reservationTo;
    private Timestamp openFrom;
    private Timestamp openTo;

    public Reservation(Integer id, Integer idCustomer, Integer idTable, Integer numberOfSeats, Timestamp reservationFrom, Timestamp reservationTo, Timestamp openFrom, Timestamp openTo) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.idTable = idTable;
        this.numberOfSeats = numberOfSeats;
        this.reservationFrom = reservationFrom;
        this.reservationTo = reservationTo;
        this.openFrom = openFrom;
        this.openTo = openTo;
    }


    public Timestamp getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(Timestamp openFrom) {
        this.openFrom = openFrom;
    }

    public Timestamp getOpenTo() {
        return openTo;
    }

    public void setOpenTo(Timestamp openTo) {
        this.openTo = openTo;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public Integer getIdTable() {
        return idTable;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public Timestamp getReservationFrom() {
        return reservationFrom;
    }

    public Timestamp getReservationTo() {
        return reservationTo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setIdTable(Integer idTable) {
        this.idTable = idTable;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setReservationFrom(Timestamp reservationFrom) {
        this.reservationFrom = reservationFrom;
    }

    public void setReservationTo(Timestamp reservationTo) {
        this.reservationTo = reservationTo;
    }
}

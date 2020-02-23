package com.example.restaurantmanagement.entity;

public class Table {
    private Integer id;
    private Integer numSeats;
    private String description;

    public Table(Integer id, Integer numSeats, String description) {
        this.id = id;
        this.numSeats = numSeats;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumSeats() {
        return numSeats;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
}

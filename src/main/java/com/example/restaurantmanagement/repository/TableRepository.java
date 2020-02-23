package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.entity.Table;

import java.util.List;

public interface TableRepository {
    List<Table> getTables();
    int addTable(Table table);
}

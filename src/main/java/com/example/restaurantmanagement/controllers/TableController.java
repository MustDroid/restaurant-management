package com.example.restaurantmanagement.controllers;

import com.example.restaurantmanagement.connections.MySQLDatabaseConnection;
import com.example.restaurantmanagement.entity.Table;
import com.example.restaurantmanagement.repository.DatabaseTableRepository;
import com.example.restaurantmanagement.repository.TableRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class TableController {

    private TableRepository tableRepository = new DatabaseTableRepository(MySQLDatabaseConnection.getInstance().getConnection());
    @CrossOrigin
    @GetMapping("/tables")
    public ResponseEntity<List> getTables() {
        List<Table> tables = tableRepository.getTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/tables")
    public Table addTable(@RequestBody Table table) {
        int id = tableRepository.addTable(table);
        table.setId(id);
        return table;
    }
}

package com.example.restaurantmanagement.controllers;

import com.example.restaurantmanagement.connections.MySQLDatabaseConnection;
import com.example.restaurantmanagement.entity.Customer;
import com.example.restaurantmanagement.entity.Table;
import com.example.restaurantmanagement.repository.CustomerRepository;
import com.example.restaurantmanagement.repository.DatabaseCustomerRepository;
import com.example.restaurantmanagement.repository.DatabaseTableRepository;
import com.example.restaurantmanagement.repository.TableRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class CustomerController {
    private CustomerRepository customerRepository = new DatabaseCustomerRepository(MySQLDatabaseConnection.getInstance().getConnection());

    @CrossOrigin
    @GetMapping("/customers")
    public ResponseEntity<List> getCustomers() {
        List<Customer> customers = customerRepository.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        customerRepository.addCustomer(customer);
        int id = customerRepository.addCustomer(customer);
        customer.setId(id);
        return customer;
    }

    @CrossOrigin
    @GetMapping("/customerById")
    public ResponseEntity<Customer> getCustomerById(@RequestParam int id) {
        Customer customer = customerRepository.findById(id);
        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}

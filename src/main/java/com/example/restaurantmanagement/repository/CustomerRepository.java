package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getCustomers();
    int  addCustomer(Customer customer);
    Customer findById(int id);

}

package com.codegym.service;

import com.codegym.model.Customer;

import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer> findAll();

    Optional<Customer> findById(int id);

    Customer save(Customer customer);

    void remove(int id);
}

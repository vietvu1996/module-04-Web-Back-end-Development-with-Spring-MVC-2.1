package com.customer_management.service;

import com.customer_management.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(long id);
    void save(Customer customer);
}

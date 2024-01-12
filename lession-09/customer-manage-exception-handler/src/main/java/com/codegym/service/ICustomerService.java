package com.codegym.service;

import com.codegym.exception.DuplicateEmailException;
import com.codegym.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer) throws DuplicateEmailException;

    Customer findById(int id);

    void remove(int id);
}

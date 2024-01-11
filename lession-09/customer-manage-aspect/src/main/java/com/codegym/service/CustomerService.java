package com.codegym.service;

import com.codegym.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Override
    public List<Customer> findAll() throws Exception {
        throw new Exception("a dumy exception");
    }

    @Override
    public Customer findOne(int id) throws Exception {
        Customer customer = new Customer();
        if (customer.getName() == null) {
            throw new Exception("customer not found !");
        }
        return customer;
    }
}

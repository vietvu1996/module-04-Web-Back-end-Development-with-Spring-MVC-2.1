package org.example.list_customer_management.service;

import org.example.list_customer_management.bean.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    Customer list(int id);
}

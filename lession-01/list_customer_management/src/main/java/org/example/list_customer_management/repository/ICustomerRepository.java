package org.example.list_customer_management.repository;

import org.example.list_customer_management.bean.Customer;

import java.util.List;

public interface ICustomerRepository {
        List<Customer> findAll();
        Customer list(int id);
}

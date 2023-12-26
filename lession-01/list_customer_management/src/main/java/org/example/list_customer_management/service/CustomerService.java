package org.example.list_customer_management.service;

import org.example.list_customer_management.bean.Customer;
import org.example.list_customer_management.repository.ICustomerRepository;
import org.example.list_customer_management.repository.impl.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
 @Service
public class CustomerService implements ICustomerService{
    private ICustomerRepository customerRepository ;

    public CustomerService() {
        customerRepository = new CustomerRepository();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer list(int id) {
        return customerRepository.list(id);
    }
}

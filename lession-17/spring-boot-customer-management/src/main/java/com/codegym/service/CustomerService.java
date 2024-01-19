package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    private final ICustomerRepository repository;

    public CustomerService(ICustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        repository.save(customer);
    }

    @Override
    public void remove(int id) {
        repository.deleteById(id);
    }
}

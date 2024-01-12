package com.codegym.service;

import com.codegym.exception.DuplicateEmailException;
import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private final ICustomerRepository repository;

    public CustomerService(ICustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Customer customer) throws DuplicateEmailException {
        try {
            repository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void remove(int id) {
        repository.deleteById(id);
    }
}

package com.codegym.service.impl;

import com.codegym.model.SmartPhone;
import com.codegym.repository.ISmartPhoneRepository;
import com.codegym.service.ISmartPhoneService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmartPhoneService implements ISmartPhoneService {
    private final ISmartPhoneRepository repository;

    public SmartPhoneService(ISmartPhoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<SmartPhone> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<SmartPhone> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public SmartPhone save(SmartPhone smartPhone) {
        return repository.save(smartPhone);
    }

    @Override
    public void remove(int id) {
        repository.deleteById(id);
    }
}

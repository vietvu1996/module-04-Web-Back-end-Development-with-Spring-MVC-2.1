package com.codegym.service;

import com.codegym.model.SmartPhone;

import java.util.Optional;

public interface ISmartPhoneService {
    Iterable<SmartPhone> findAll();

    Optional<SmartPhone> findById(int id);

    SmartPhone save(SmartPhone smartPhone);

    void remove(int id);
}

package com.codegym.service;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    void save(T t);

    Optional<T> findById(int id);

    void remove(int id);
}

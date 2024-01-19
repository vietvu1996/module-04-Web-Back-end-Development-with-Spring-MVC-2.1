package com.codegym.service;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    Optional<T> findById(int id);

    void save(T t);

    void remove(int id);
}

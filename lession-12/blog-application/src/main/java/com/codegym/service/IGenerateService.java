package com.codegym.service;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    void save(T t);

    Optional<T> findById(int id);

    void remove(int id);

    Iterable<T> findAll(Sort sort);
}

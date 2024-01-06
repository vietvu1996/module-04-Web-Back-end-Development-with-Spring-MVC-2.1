package com.codegym.repository;

import java.util.List;

public interface IGenerateRepository<T> {
    List<T> findAll();

    T findById(int id);

    void save(T t);

    void remove(int id);
}

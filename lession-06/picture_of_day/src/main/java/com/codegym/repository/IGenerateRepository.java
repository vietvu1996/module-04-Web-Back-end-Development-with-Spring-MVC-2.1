package com.codegym.repository;

import java.util.List;

public interface IGenerateRepository<T> {
    List<T> findAll();

    void save(T t);

    T findById(int id);

    T like(int id);

    void remove(int id);
}

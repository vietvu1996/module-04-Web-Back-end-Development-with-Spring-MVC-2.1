package com.codegym.repository;

import com.codegym.model.Blog;

import java.util.List;

public interface IGenerateRepository<T> {
    List<T> findAll();

    void save(T t);

    T findById(int id);

    void remove(int id);
}

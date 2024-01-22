package com.codegym.service;

import com.codegym.model.Brand;
import com.codegym.model.Category;
import com.codegym.model.Product;

import java.util.List;

public interface ICategoryService {
    Iterable<Category> findAllByBrand(Brand brand);

    Category findById(Long id);

    Iterable<Category> findAll();
}

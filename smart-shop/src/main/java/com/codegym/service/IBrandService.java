package com.codegym.service;

import com.codegym.model.Brand;
import com.codegym.model.Product;

import java.util.List;

public interface IBrandService {
    List<Brand> findAll();

    Brand findById(Long id);

    List<Product> sortProductByName(Long id);

    List<Product> sortProductsByPriceAsc(Long id);

    List<Product> sortProductsByPriceDesc(Long id);


}

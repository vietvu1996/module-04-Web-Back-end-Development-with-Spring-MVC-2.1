package com.codegym.service;

import com.codegym.model.Brand;
import com.codegym.model.Category;
import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<Product> findAllByBrand(Brand brand);

    List<Product> findAllByCategory(Category category);

    Product findById(Long id);

    Iterable<Product> findAll();

    List<Product> sortByName();

    List<Product> sortByIncreasePrice();

    List<Product> sortByDecreasePrice();

    List<Product> searchProductByName(String name);

    List<Product> searchProductByBrandName(String brandName);

    List<Product> searchProductByCategoryContaining(String categoryName);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findProductByName(Product product, Pageable pageable);

    Page<Product> findProductByCategory(Category category, Pageable pageable);

    Page<Product> findProductByBrand(Brand brand, Pageable pageable);
}

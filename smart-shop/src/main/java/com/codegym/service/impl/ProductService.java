package com.codegym.service.impl;

import com.codegym.model.Brand;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.repository.ICategoryRepository;
import com.codegym.repository.IProductRepository;
import com.codegym.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    private final ICategoryRepository categoryRepository;


    public ProductService(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAllByBrand(Brand brand) {
        Iterable<Category> categories = categoryRepository.findAllByBrand(brand);
        List<Product> result = new ArrayList<>();
        for (Category category : categories) {
            Iterable<Product> products = productRepository.findAllByCategory(category);
            products.forEach(result::add);
        }
        return result;
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> sortByName() {
        List<Product> products = (List<Product>) findAll();
        return products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByIncreasePrice() {
        List<Product> products = (List<Product>) findAll();
        return products.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByDecreasePrice() {
        List<Product> products = (List<Product>) findAll();
        return products.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Product> searchProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public List<Product> searchProductByBrandName(String brandName) {
        return productRepository.findProductByBrandName(brandName);
    }

    @Override
    public List<Product> searchProductByCategoryContaining(String categoryName) {
        return productRepository.findProductByCategoryContaining(categoryName);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findProductByName(Product product, Pageable pageable) {
        return productRepository.findProductByName(product, pageable);
    }

    @Override
    public Page<Product> findProductByCategory(Category category, Pageable pageable) {
        return productRepository.findProductByCategory(category, pageable);
    }

    @Override
    public Page<Product> findProductByBrand(Brand brand, Pageable pageable) {
        return productRepository.findProductByBrand(brand, pageable);
    }
}

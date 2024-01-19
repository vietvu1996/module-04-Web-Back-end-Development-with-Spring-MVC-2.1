package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public void remove(int id) {
        repository.deleteById(id);
    }
}

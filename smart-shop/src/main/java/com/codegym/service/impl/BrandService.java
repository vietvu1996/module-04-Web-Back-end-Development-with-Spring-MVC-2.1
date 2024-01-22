package com.codegym.service.impl;

import com.codegym.model.Brand;
import com.codegym.model.Product;
import com.codegym.repository.IBrandRepository;
import com.codegym.service.IBrandService;
import com.codegym.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService implements IBrandService {
    private final IBrandRepository brandRepository;

    private final IProductService productService;


    public BrandService(IBrandRepository brandRepository, IProductService productService) {
        this.brandRepository = brandRepository;
        this.productService = productService;
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> sortProductByName(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        List<Product> products = productService.findAllByBrand(brand);
        return products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
    }

    @Override
    public List<Product> sortProductsByPriceAsc(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        List<Product> products = productService.findAllByBrand(brand);
        return products.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
    }

    @Override
    public List<Product> sortProductsByPriceDesc(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        List<Product> products = productService.findAllByBrand(brand);
        return products.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());
    }

}

package com.codegym.repository;

import com.codegym.model.Brand;
import com.codegym.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICategoryRepository extends PagingAndSortingRepository<Category, Long> {
    List<Category> findAllByBrand(Brand brand);

}

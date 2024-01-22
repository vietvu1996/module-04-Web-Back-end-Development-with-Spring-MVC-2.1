package com.codegym.repository;

import com.codegym.model.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBrandRepository extends PagingAndSortingRepository<Brand, Long> {

}

package com.codegym.repository;

import com.codegym.model.Brand;
import com.codegym.model.Category;
import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);

    @Query(nativeQuery = true,
            value = "SELECT * FROM product " +
                    "INNER JOIN category ON product.category_id = category.id " +
                    "INNER JOIN brand ON brand.id = category.brand_id " +
                    "WHERE brand.name = :brandName")
    List<Product> findProductByBrandName(@Param("brandName") String brandName);

    List<Product> findProductByName(String name);

    List<Product> findProductByCategoryContaining(String categoryName);

    Page<Product> findProductByName(Product product, Pageable pageable);

    Page<Product> findProductByCategory(Category category, Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "JOIN p.category c " +
            "JOIN c.brand b " +
            "WHERE b = :brand")
    Page<Product> findProductByBrand(Brand brand, Pageable pageable);
}

package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBlogRepository extends PagingAndSortingRepository<Blog, Integer> {
    Iterable<Blog> findAllByCategory(Category category);

    Iterable<Blog> findAllByContent(String content);

    Iterable<Blog> findByNameContainingOrNameContaining(String content, String name);

    Page<Blog> findByCategory(Category category, Pageable pageable);


}

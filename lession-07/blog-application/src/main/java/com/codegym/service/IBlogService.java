package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IBlogService extends IGenerateService<Blog> {
    Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAll(Pageable pageable);

    Iterable<Blog> findAllByContent(String content);

    Iterable<Blog> findAllContainingContentOrContainingName(String content, String name);

    Page<Blog> findByCategory(Category category, Pageable pageable);
}

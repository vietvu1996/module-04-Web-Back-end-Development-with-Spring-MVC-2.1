package com.codegym.service.impl;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.IBlogRepository;
import com.codegym.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    private final IBlogRepository blogRepository;
    @Autowired

    public BlogService(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Iterable<Blog> findAllByCategory(Category category) {
        return blogRepository.findAllByCategory(category);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
            return blogRepository.findAll(pageable);
    }

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Iterable<Blog> findAllByContent(String content) {
        return blogRepository.findAllByContent(content);
    }

    @Override
    public Iterable<Blog> findAllContainingContentOrContainingName(String content, String name) {
        return blogRepository.findByNameContainingOrNameContaining(content,name);
    }

    @Override
    public Page<Blog> findByCategory(Category category, Pageable pageable) {
        return blogRepository.findByCategory(category, pageable);
    }

    @Override
    public Iterable<Blog> findAll(Sort sort) {
        return blogRepository.findAll(sort);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public Optional<Blog> findById(int id) {
        return blogRepository.findById(id);
    }

    @Override
    public void remove(int id) {
        blogRepository.deleteById(id);
    }
}

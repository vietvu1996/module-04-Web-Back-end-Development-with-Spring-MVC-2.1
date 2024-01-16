package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IBlogService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    private final IBlogService blogService;
    private final ICategoryService categoryService;

    @Autowired
    public BlogController(IBlogService blogService, ICategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public Iterable<Category> listCategories() {
        return categoryService.findAll();
    }

    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView view = new ModelAndView("blog/list");
        Iterable<Blog> blogs = blogService.findAll();
        view.addObject("blogs", blogs);
        return view;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView view = new ModelAndView("blog/create");
        view.addObject("blog", new Blog());
        return view;
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute("blog") Blog blog, RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Created new blog successfully !");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/update")
    public ModelAndView showUpdateForm(@PathVariable int id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            Iterable<Category> categories = categoryService.findAll();
            ModelAndView view = new ModelAndView("blog/update");
            view.addObject("blog", blog.get());
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }

    @PostMapping("/update")
    public String updateBlog(@ModelAttribute("blog") Blog blog, RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Updated blog successfully !");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteBlog(@PathVariable int id, RedirectAttributes redirect) {
        blogService.remove(id);
        redirect.addFlashAttribute("message", "Removed blog successfully !");
        return new ModelAndView("redirect:/blogs");
    }


    @GetMapping("/{id}/view")
    public ModelAndView viewBlog(@PathVariable int id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView view = new ModelAndView("blog/view");
            view.addObject("blog", blog.get());
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search") String search) {
        Iterable<Blog> blogs = blogService.findAllContainingContentOrContainingName(search.trim(), search.trim());
        ModelAndView view = new ModelAndView("blog/list");
        view.addObject("blogs", blogs);
        return view;
    }

    @GetMapping("/{categoryId}")
    public ModelAndView searchByCategory(@PathVariable("categoryId") int categoryId) {
        Sort sort = Sort.by(Sort.Order.asc("content"));
        Pageable pageable = PageRequest.of(0, 1, sort);
        Optional<Category> category = categoryService.findById(categoryId);
        Iterable<Blog> blogs = blogService.findByCategory(category.get(), pageable);
        ModelAndView view = new ModelAndView("blog/list");
        view.addObject("blogs", blogs);
        return view;
    }

    @GetMapping("/page")
    public ModelAndView page(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, 1);
        Page<Blog> blogPage = blogService.findAll(pageable);
        ModelAndView view = new ModelAndView("blog/list");
        view.addObject("blogPage", blogPage);
        return view;
    }
}

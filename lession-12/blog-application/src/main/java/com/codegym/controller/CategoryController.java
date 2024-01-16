package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IBlogService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final ICategoryService categoryService;
    private final IBlogService blogService;
    @Autowired
    public CategoryController(ICategoryService categoryService, IBlogService blogService) {
        this.categoryService = categoryService;
        this.blogService = blogService;
    }

    @GetMapping("")
    public ModelAndView listCategory() {
        ModelAndView view = new ModelAndView("category/list");
        Iterable<Category> categories = categoryService.findAll();
        view.addObject("categories", categories);
        return view;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView view = new ModelAndView("category/create");
        view.addObject("category", new Category());
        return view;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("category") Category category, RedirectAttributes redirect) {
        categoryService.save(category);
        redirect.addFlashAttribute("message", "Create new category");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateForm(@PathVariable int id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView view = new ModelAndView("category/update");
            view.addObject("category", category.get());
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("category") Category category, RedirectAttributes redirect) {
        categoryService.save(category);
        redirect.addFlashAttribute("message", "Update category successfully !");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewCategory(@PathVariable("id") int id) {
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ModelAndView("error-404");
        } else {
            Iterable<Blog> blogs = blogService.findAllByCategory(category.get());
            ModelAndView view = new ModelAndView("blog/list");
            view.addObject("blogs", blogs);
            return view;
        }
    }
}

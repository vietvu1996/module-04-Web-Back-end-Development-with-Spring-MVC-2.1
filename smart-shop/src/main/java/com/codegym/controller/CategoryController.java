package com.codegym.controller;

import com.codegym.model.Brand;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.IBrandService;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    private final IBrandService brandService;

    private final IProductService productService;

    public CategoryController(ICategoryService categoryService, IBrandService brandService, IProductService productService) {
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.productService = productService;
    }

    @GetMapping("")
    public ModelAndView showAllCategory() {
        ModelAndView view = new ModelAndView("category/list");
        view.addObject("categories", categoryService.findAll());
        return view;
    }

    @GetMapping("/{id}/view")
    public ModelAndView findCategory(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        List<Product> products = productService.findAllByCategory(category);
        ModelAndView view = new ModelAndView("category/view");
        view.addObject("category", category);
        view.addObject("products", products);
        return view;
    }

    @GetMapping("/{id}/brand")
    public ModelAndView getCategoryByBrand(@PathVariable Long id) {
        Brand brand = brandService.findById(id);
        Iterable<Category> categories = categoryService.findAllByBrand(brand);
        ModelAndView view = new ModelAndView("category/brand");
        view.addObject("categories", categories);
        return view;
    }
}

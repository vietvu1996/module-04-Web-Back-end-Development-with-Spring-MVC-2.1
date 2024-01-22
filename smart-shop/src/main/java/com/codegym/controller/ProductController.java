package com.codegym.controller;

import com.codegym.model.Brand;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.IBrandService;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final IBrandService brandService;

    private final ICategoryService categoryService;

    private final IProductService productService;

    public ProductController(IBrandService brandService, ICategoryService categoryService, IProductService productService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @GetMapping("")
    public ModelAndView showAllProduct() {
        ModelAndView view = new ModelAndView("product/list");
        view.addObject("products", productService.findAll());
        return view;
    }

    @ModelAttribute("brands")
    public List<Brand> brandList() {
        return brandService.findAll();
    }

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/view/sort")
    public ModelAndView sortProduct(@RequestParam String sortType) {
        ModelAndView view = new ModelAndView("product/sort");
        List<Product> sortedProducts;
        switch (sortType) {
            case "name":
                sortedProducts = productService.sortByName();
                break;

            case "priceDesc":
                sortedProducts = productService.sortByDecreasePrice();
                break;

            case "priceAsc":
                sortedProducts = productService.sortByIncreasePrice();
                break;

            default:
                sortedProducts = (List<Product>) productService.findAll();
        }
        view.addObject("sortedProducts", sortedProducts);
        view.addObject("sortType", sortType);
        return view;
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewProduct(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("product/view");
        view.addObject("product", productService.findById(id));
        return view;
    }

    @GetMapping("/{id}/brand")
    public ModelAndView findProductByBrand(@PathVariable Long id) {
        List<Product> products = productService.findAllByBrand(brandService.findById(id));
        ModelAndView view = new ModelAndView("product/list");
        view.addObject("products", products);
        return view;
    }

    @GetMapping("/{id}/category")
    public ModelAndView findProductByCategory(@PathVariable Long id) {
        List<Product> products = productService.findAllByCategory(categoryService.findById(id));
        ModelAndView view = new ModelAndView("product/list");
        view.addObject("products", products);
        return view;
    }

    @GetMapping("/search")
    public ModelAndView searchProduct(@RequestParam("search") String search) {
        String searchLowerCase = search.trim().toLowerCase();
        List<Product> productName = productService.searchProductByName(searchLowerCase);
        List<Product> products = productService.searchProductByBrandName(searchLowerCase);
        List<Product> productList = productService.searchProductByCategoryContaining(searchLowerCase);
        Set<Product> result = new HashSet<>();
        result.addAll(productName);
        result.addAll(products);
        result.addAll(productList);
        ModelAndView view = new ModelAndView("product/list");
        view.addObject("products", result);
        return view;
    }

    @GetMapping("/{categoryId}")
    public ModelAndView searchProductByCategory(@PathVariable("categoryId") Long categoryId) {
        Sort sort = Sort.by(Sort.Order.asc("content"));
        Pageable pageable = PageRequest.of(0, 1, sort);
        Category category = categoryService.findById(categoryId);
        Iterable<Product> products = productService.findProductByCategory(category, pageable);
        ModelAndView view = new ModelAndView("product/list");
        view.addObject("products", products);
        return view;
    }

    @GetMapping("/{brandId}")
    public ModelAndView searchProductByBrand(@PathVariable("brandId") Long brandId) {
        Sort sort = Sort.by(Sort.Order.asc("content"));
        Pageable pageable = PageRequest.of(0, 1, sort);
        Brand brand = brandService.findById(brandId);
        Iterable<Product> products = productService.findProductByBrand(brand, pageable);
        ModelAndView view = new ModelAndView("product/list");
        view.addObject("products", products);
        return view;
    }

    @GetMapping("/productName")
    public ModelAndView searchProductByName(@RequestParam("productName") String productName) {
        Sort sort = Sort.by(Sort.Order.asc("productName"));
        Pageable pageable = PageRequest.of(0, 5, sort);
        Product product = new Product();
        product.setName(productName);
        Page<Product> products = productService.findProductByName(product, pageable);
        ModelAndView view = new ModelAndView("product/list");
        view.addObject("products", products.getContent());
        return view;
    }

    @GetMapping("/page")
    public ModelAndView page(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, 1);
        Page<Product> productPage = productService.findAll(pageable);
        ModelAndView view = new ModelAndView("product/list");
        view.addObject("productPage", productPage);
        return view;
    }
}

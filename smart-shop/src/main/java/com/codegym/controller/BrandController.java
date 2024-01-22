package com.codegym.controller;

import com.codegym.model.Brand;
import com.codegym.model.Product;
import com.codegym.service.IBrandService;
import com.codegym.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final IBrandService brandService;

    private final IProductService productService;

    public BrandController(IBrandService brandService, IProductService productService) {
        this.brandService = brandService;
        this.productService = productService;
    }

    @GetMapping("")
    public ModelAndView showAllBrand() {
        ModelAndView view = new ModelAndView("brand/list");
        view.addObject("brands", brandService.findAll());
        return view;
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewBrand(@PathVariable Long id) {
        Brand brand = brandService.findById(id);
        List<Product> products = productService.findAllByBrand(brand);
        ModelAndView view = new ModelAndView("brand/view");
        view.addObject("brand", brand);
        view.addObject("products", products);
        return view;
    }

    @GetMapping("/view/sort")
    public ModelAndView getSortedProductForBrand(@RequestParam String sortType, @RequestParam Long brandId) {
        Brand brand = brandService.findById(brandId);
        List<Product> sortedProducts;
        switch (sortType) {
            case "name":
                sortedProducts = brandService.sortProductByName(brandId);
                break;
            case "priceDesc":
                sortedProducts = brandService.sortProductsByPriceDesc(brandId);
                break;
            case "priceAsc":
                sortedProducts = brandService.sortProductsByPriceAsc(brandId);
                break;
            default:
                sortedProducts = productService.findAllByBrand(brand);
        }
        ModelAndView view = new ModelAndView("brand/sort");
        view.addObject("sortedProducts", sortedProducts);
        view.addObject("sortType", sortType);
        return view;
    }
}

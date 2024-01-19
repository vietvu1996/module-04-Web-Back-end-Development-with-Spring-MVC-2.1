package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ModelAndView showProductList() {
        ModelAndView view = new ModelAndView("view/list");
        view.addObject("products", productService.findAll());
        return view;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView view = new ModelAndView("view/create");
        view.addObject("product", new Product());
        return view;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView view = new ModelAndView("view/create");
        view.addObject("product", new Product());
        view.addObject("message", "Created new product successfully !");
        return view;
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateForm(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView view = new ModelAndView("view/update");
            view.addObject("product", product.get());
            return view;
        }
        return new ModelAndView("error");
    }

    @PostMapping("/update")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView view = new ModelAndView("view/update");
        view.addObject("product", product);
        view.addObject("message", "Updated product successfully !");
        return view;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteForm(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView view = new ModelAndView("view/delete");
            view.addObject("product", product.get());
            return view;
        }
        return new ModelAndView("error");
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:/products";
    }
}

package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("cart")
    public Cart setUpCart() {
        return new Cart();
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView view = new ModelAndView("/shop");
        view.addObject("products", productService.findAll());
        return view;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable int id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return "error-404";
        }
        if (action.equals("show")) {
            cart.addProduct(product.get());
            return "redirect:/shopping-cart";
        }
        cart.addProduct(product.get());
        return "redirect:/shop";
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart(@SessionAttribute("cart") Cart cart) {
        ModelAndView view = new ModelAndView("/cart");
        view.addObject("cart", cart);
        return view;
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable int id, Cart cart) {
        Optional<Product> product = productService.findById(id);
        product.ifPresent(cart::removeCartItem);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewCart(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView view = new ModelAndView("view");
            view.addObject("product", product.orElse(null));
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }

    @GetMapping("/{id}/update")
    public ModelAndView showUpdateForm(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView view = new ModelAndView("update");
            view.addObject("product", product.orElse(null));
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam("quantity") Integer quantity, Product product, Cart cart) {
        cart.updateCartItem(product, quantity);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Cart cart) {
        cart.deleteCartItem(productService.findById(id).get());
        return "redirect:/shopping-cart";
    }
}

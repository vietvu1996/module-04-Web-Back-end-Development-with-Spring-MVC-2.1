package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.ICustomerService;
import com.codegym.service.IProvinceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final ICustomerService customerService;

    private final IProvinceService provinceService;

    public CustomerController(ICustomerService customerService, IProvinceService provinceService) {
        this.customerService = customerService;
        this.provinceService = provinceService;
    }

    @ModelAttribute("provinces")
    public Iterable<Province> listProvinces() {
        return provinceService.findAll();
    }

    @GetMapping("")
    public ModelAndView listCustomer() {
        ModelAndView view = new ModelAndView("customer/list");
        Iterable<Customer> customers = customerService.findAll();
        view.addObject("customers", customers);
        return view;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView view = new ModelAndView("customer/create");
        view.addObject("customer", new Customer());
        return view;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("customer") Customer customer, RedirectAttributes redirect) {
        customerService.save(customer);
        redirect.addFlashAttribute("message", "Create new customer successfully !");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        customerService.remove(id);
        redirect.addFlashAttribute("message", "Removed customer successfully !");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateForm(@PathVariable int id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView view = new ModelAndView("customer/update");
            view.addObject("customer", customer.get());
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("customer") Customer customer, RedirectAttributes redirect) {
        customerService.save(customer);
        redirect.addFlashAttribute("message", "Update customer successfully");
        return "redirect:/customers";
    }
}

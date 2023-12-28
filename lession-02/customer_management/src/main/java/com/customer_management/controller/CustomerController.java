package com.customer_management.controller;

import com.customer_management.model.Customer;
import com.customer_management.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")

public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping()
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView showInformation(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("info");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping
    public String updateCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }
}

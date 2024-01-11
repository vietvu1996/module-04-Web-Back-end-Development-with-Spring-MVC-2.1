package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ModelAndView showList() {
        try {
            ModelAndView view = new ModelAndView("customer/list");
            List<Customer> customers = customerService.findAll();
            view.addObject("customers", customers);
            return view;
        } catch (Exception e) {
            return new ModelAndView("redirect:/customers");
        }
    }

    @GetMapping("/{id}")
    public ModelAndView showInformation(@PathVariable int id) {
        try {
            ModelAndView view = new ModelAndView("customer/info");
            Customer customer = customerService.findOne(id);
            view.addObject("customer", customer);
            return view;
        } catch (Exception e) {
            return new ModelAndView("redirect:/customers");
        }
    }
}

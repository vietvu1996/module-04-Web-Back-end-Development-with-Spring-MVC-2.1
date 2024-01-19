package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView view = new ModelAndView("customer/list");
        view.addObject("customers", customerService.findAll());
        return view;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView view = new ModelAndView("/customer/create");
        view.addObject("customer", new Customer());
        return view;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView view = new ModelAndView("/customer/create");
        view.addObject("customer", new Customer());
        view.addObject("message", "Created customer successfully !");
        return view;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable int id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView view = new ModelAndView("customer/update");
            view.addObject("customer", customer.get());
            return view;
        }
        return new ModelAndView("error-404");
    }

    @PostMapping("/update")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView view = new ModelAndView("customer/update");
        view.addObject("customer", customer);
        view.addObject("message", "Updated customer successfully !");
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView view = new ModelAndView("customer/delete");
            view.addObject("customer", customer.get());
            return view;
        }
        return new ModelAndView("error-404");
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:/customers";
    }
}
